/*
 * Sistema de Ouvidoria: um canal através do qual os usuários
 * podem encaminhar suas reclamações, elogios e sugestões.
 *
 * Copyright (C) 2011 SERPRO
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; tanto a versão 2 da
 * Licença como (a seu critério) qualquer versão mais nova.
 *
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU,
 * sob o título "LICENCA.txt", junto com esse programa. Se não,
 * acesse o Portal do Software Público Brasileiro no endereço
 * http://www.softwarepublico.gov.br/ ou escreva para a Fundação do
 * Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston,
 * MA 02111-1301, USA.
 *
 * Contatos através do seguinte endereço internet:
 * http://www.serpro.gov.br/sistemaouvidoria/
 */
package br.gov.serpro.ouvidoria.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.gov.serpro.ouvidoria.dao.Dao;
import br.gov.serpro.ouvidoria.dao.DaoException;
import br.gov.serpro.ouvidoria.dao.DaoFactory;
import br.gov.serpro.ouvidoria.model.Ajuda;
import br.gov.serpro.ouvidoria.model.Funcionalidade;
import br.gov.serpro.ouvidoria.model.Funcionario;
import br.gov.serpro.ouvidoria.util.Utilitario;

/**
 * Persistir e consultar objetos da classe Ajuda
 *
 * @author SERPRO
 * @version $Revision: 1.1.2.3 $, $Date: 2011/10/19 18:18:09 $
 * @version 0.1, Date: 2004/12/13
 */
public class AjudaCtrl {

	private static final String SELECT_A_FROM_AJUDA_AS_A = " select a from Ajuda as a ";
	private static final String ORDER_BY_A_DESCRICAO = " order by a.descricao ";
	private static final String AND_A_FUNCIONALIDADE_IN_ELEMENTS_F_LISTA_FUNCIONALIDADE_AND_F_ID = " and a.funcionalidade in elements(f.listaFuncionalidade) and f.id = ";
	private static final String AND_A_GRUPO = " and a.grupo = ";
	private static final String WHERE_A_TIPO_IN = " where a.tipo in ('";
	private static final String FUNCIONARIO_AS_F = " , Funcionario as f ";
	private Dao ajudaDao;

	/**
	 * Construtor recebendo objeto Dao
	 *
	 * @param daoFactory
	 */
	public AjudaCtrl(final DaoFactory daoFactory) {
		ajudaDao = daoFactory.create(Ajuda.class);
	}

	/**
	 * Obtém a entidade à partir do ID recebido como parâmetro
	 *
	 * @param id
	 * @return objeto da classe Ajuda
	 * @throws DaoException
	 */
	public Ajuda get(final Long id) throws DaoException {
		if (id == null) {
			throw new DaoException("ID do Objeto não pode ser nulo.");
		}
		return (Ajuda) ajudaDao.get(id);
	}

	/**
	 * Método para listar os objetos Ajuda
	 */
	public List list() throws DaoException {
		return ajudaDao.list();
	}

	/**
	 * Método para consultar ajuda de uma determinada funcionalidade
	 *
	 * @param funcionario
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public List consultaAjuda(final Funcionario funcionario, final String tipo)
			throws DaoException {

		StringBuffer sql = new StringBuffer();

		sql.append(" Select a from Ajuda as a ");

		if (funcionario != null) {
			sql.append(FUNCIONARIO_AS_F);
		}

		sql.append(WHERE_A_TIPO_IN + Ajuda.TIPO_AMBOS + "','" + tipo
				+ "')");

		if (funcionario == null) {
			sql.append(AND_A_GRUPO + Ajuda.GRUPO_GERAL);
		} else {
			sql.append(AND_A_FUNCIONALIDADE_IN_ELEMENTS_F_LISTA_FUNCIONALIDADE_AND_F_ID
					+ funcionario.getId());
		}

		if (tipo.equals(Ajuda.TIPO_INDICE)) {
			sql.append(ORDER_BY_A_DESCRICAO);
		} else {
			sql.append(" order by a.ordem ");
		}

		return ajudaDao.query(sql.toString());
	}

	/**
	 * Método para montar a árvore de conteúdo de ajuda
	 *
	 * @param funcionario
	 * @return
	 * @throws DaoException
	 */
	public List montaArvoreConteudo(final Funcionario funcionario)
			throws DaoException {

		StringBuffer sql = new StringBuffer();

		sql.append(SELECT_A_FROM_AJUDA_AS_A);

		if (funcionario != null) {
			sql.append(FUNCIONARIO_AS_F);
		}

		sql.append(" where a.ajudaPai is null ");

		if (funcionario == null) {
			sql.append(AND_A_GRUPO + Ajuda.GRUPO_GERAL);
		} else {
			sql.append(AND_A_FUNCIONALIDADE_IN_ELEMENTS_F_LISTA_FUNCIONALIDADE_AND_F_ID
					+ funcionario.getId());
		}

		sql.append(" and a.tipo in ('" + Ajuda.TIPO_AMBOS + "','"
				+ Ajuda.TIPO_CONTEUDO + "' )");

		sql.append(ORDER_BY_A_DESCRICAO);

		List lstAjudaRaiz = ajudaDao.query(sql.toString());

		if (lstAjudaRaiz == null || lstAjudaRaiz.isEmpty()) {
			return new ArrayList();
		}

		List lstArvoreAjuda = new ArrayList();

		for (Iterator it = lstAjudaRaiz.iterator(); it.hasNext();) {
			Ajuda ajuda = (Ajuda) it.next();
			ajuda.setNivel(1);
			lstArvoreAjuda.add(ajuda);
		}

		return lstArvoreAjuda;
	}

	/**
	 * Recupera conteúdo da ajuda para uma funcionalidade
	 *
	 * @param funcionalidade
	 * @param funcionario
	 * @return
	 * @throws DaoException
	 */
	public Ajuda get(final Funcionalidade funcionalidade,
			final Funcionario funcionario) throws DaoException {

		if (funcionalidade == null) {
			throw new NullPointerException();
		}

		StringBuffer sql = new StringBuffer();

		sql.append(SELECT_A_FROM_AJUDA_AS_A);

		if (funcionario != null) {
			sql.append(FUNCIONARIO_AS_F);
		}

		sql.append(WHERE_A_TIPO_IN + Ajuda.TIPO_AMBOS + "','"
				+ Ajuda.TIPO_CONTEUDO + "' )");

		if (funcionario == null) {
			sql.append(AND_A_GRUPO + Ajuda.GRUPO_GERAL);
		} else {
			sql.append(AND_A_FUNCIONALIDADE_IN_ELEMENTS_F_LISTA_FUNCIONALIDADE_AND_F_ID
					+ funcionario.getId());
		}

		sql.append(" and a.funcionalidade.id = " + funcionalidade.getId());
		sql.append(ORDER_BY_A_DESCRICAO);

		List lstAjuda = ajudaDao.query(sql.toString());

		if (lstAjuda == null || lstAjuda.isEmpty()) {
			return null;
		}

		return (Ajuda) lstAjuda.get(0);
	}

	/**
	 * Consulta ajuda a partir de texto informado
	 *
	 * @param funcionario
	 * @param texto
	 * @return
	 * @throws DaoException
	 */
	public List consultaTextoAjuda(final Funcionario funcionario,
			final String texto) throws DaoException {

		StringBuffer sql = new StringBuffer();

		sql.append(SELECT_A_FROM_AJUDA_AS_A);

		if (funcionario != null) {
			sql.append(FUNCIONARIO_AS_F);
		}

		sql.append(WHERE_A_TIPO_IN + Ajuda.TIPO_AMBOS + "','"
				+ Ajuda.TIPO_CONTEUDO + "' )");

		if (funcionario == null) {
			sql.append(AND_A_GRUPO + Ajuda.GRUPO_GERAL);
		} else {
			sql.append(AND_A_FUNCIONALIDADE_IN_ELEMENTS_F_LISTA_FUNCIONALIDADE_AND_F_ID
					+ funcionario.getId());
		}

		sql.append(" and a.descricao LIKE '%" + Utilitario.trataPlic(texto)
				+ "%'");

		sql.append(ORDER_BY_A_DESCRICAO);

		return ajudaDao.query(sql.toString());
	}

}