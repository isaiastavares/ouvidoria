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

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import br.gov.serpro.ouvidoria.bean.MenuBean;
import br.gov.serpro.ouvidoria.dao.DaoFactory;
import br.gov.serpro.ouvidoria.model.Funcionalidade;
import br.gov.serpro.ouvidoria.model.Funcionario;

/**
 * MenuCtrl
 *
 * Objetivo: Responsável por montar o MenuBean do Funcionário
 *
 * @author SERPRO
 * @version $Revision: 1.1.2.3 $, $Date: 2011/10/19 18:18:09 $
 * @version 0.1, Date: 2004/12/13
 */
public class MenuCtrl {

	/**
	 * Construtor recebendo objeto Dao
	 *
	 * @param daoFactory
	 */
	public MenuCtrl(final DaoFactory daoFactory) {
	}

	/**
	 * Recupera os sub-itens do menu
	 *
	 * @param funcionalidade
	 * @return List com os sub-itens do menu
	 */
	private SortedSet getSubMenu(final Funcionario funcionario,
			final Funcionalidade funcionalidade) {
		SortedSet subMenu = new TreeSet();
		Collection lista = funcionalidade.getListaSubItem();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			Funcionalidade subItem = (Funcionalidade) iter.next();
			if (subItem.getItemMenu().booleanValue()
					&& funcionario.getListaFuncionalidade().contains(subItem)) {
				MenuBean itemMenu = new MenuBean(subItem.getDescricao(),
						subItem.getPath(), subItem.getPathImageHi(),
						subItem.getPathImageLo(), subItem.getImageWidth(),
						subItem.getOrdemItemMenu());
				itemMenu.setSubMenu(getSubMenu(funcionario, subItem));
				subMenu.add(itemMenu);
			}
		}
		return subMenu;
	}

	/**
	 * Monta o menu do funcionário
	 *
	 * @param funcionario
	 * @return List com o menu do funcionário
	 */
	public SortedSet getMenu(final Funcionario funcionario) {

		SortedSet menu = new TreeSet();

		Collection lista = funcionario.getListaFuncionalidade();

		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			Funcionalidade funcionalidade = (Funcionalidade) iter.next();
			if (funcionalidade.getItemMenu().booleanValue()
					&& funcionalidade.getItemPai() == null) {

				MenuBean item = new MenuBean(funcionalidade.getDescricao(),
						funcionalidade.getPath(),
						funcionalidade.getPathImageHi(),
						funcionalidade.getPathImageLo(),
						funcionalidade.getImageWidth(),
						funcionalidade.getOrdemItemMenu());

				item.setSubMenu(getSubMenu(funcionario, funcionalidade));

				menu.add(item);

			}
		}

		return menu;
	}

}