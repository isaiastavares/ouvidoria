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
package br.gov.serpro.ouvidoria.agendador;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.gov.serpro.ouvidoria.controller.AcionamentoCtrl;
import br.gov.serpro.ouvidoria.controller.gerencial.administracao.OrgaoCtrl;
import br.gov.serpro.ouvidoria.controller.gerencial.administracao.ParametrosGeraisCtrl;
import br.gov.serpro.ouvidoria.dao.DaoException;
import br.gov.serpro.ouvidoria.dao.DaoFactory;
import br.gov.serpro.ouvidoria.dao.hibernate.HibernateDaoFactory;
import br.gov.serpro.ouvidoria.model.Acionamento;
import br.gov.serpro.ouvidoria.model.ConfiguracoesOrgao;
import br.gov.serpro.ouvidoria.model.EstadoAcionamento;
import br.gov.serpro.ouvidoria.model.Funcionario;
import br.gov.serpro.ouvidoria.model.Orgao;
import br.gov.serpro.ouvidoria.model.ParametrosGerais;
import br.gov.serpro.ouvidoria.util.Constants;
import br.gov.serpro.ouvidoria.util.Utilitario;

/**
 * <p>
 * This is just a simple job that gets fired off many times by example 1
 * </p>
 *
 * @author Bill Kratzer
 */
public class EnviarAlertaJob implements Job {

	private static final Log LOG = LogFactory.getLog(EnviarAlertaJob.class);

	private static final String BG_COLOR_FFFFFF = "	 <td bgcolor='#FFFFFF'>";
	private static final String TARGET_BLANK = "' target='_blank'>";

	public static final String ID_ORGAO = "idOrgao";
	public static final String URL = "url";

	final DaoFactory daoFactory = new HibernateDaoFactory();
	protected String enderecoUrl;

	/**
	 * Quartz requires a public empty constructor so that the scheduler can
	 * instantiate the class whenever it needs.
	 */
	public EnviarAlertaJob() {
		// Do nothing
	}

	/**
	 * <p>
	 * Called by the <code>{@link org.quartz.Scheduler}</code> when a
	 * <code>{@link org.quartz.Trigger}</code> fires that is associated with the
	 * <code>Job</code>.
	 * </p>
	 *
	 * @throws JobExecutionException
	 *             if there is an exception while executing the job.
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		String jobName = context.getJobDetail().getFullName();
		LOG.info("\n\nExecutando JOB: " + jobName + " em " + new Date()
				+ "\n\n");

		try {

			JobDataMap data = context.getJobDetail().getJobDataMap();
			String id = data.getString(ID_ORGAO);
			enderecoUrl = data.getString(URL);

			OrgaoCtrl orgaoCtrl = new OrgaoCtrl(daoFactory);
			Orgao orgao = orgaoCtrl.get(id);

			this.enviarAlertaViaEmail(orgao, EstadoAcionamento.PENDENTE);
			LOG.info("\nEnviadas Notificacoes sobre Acionamentos Pendentes - "
					+ new Date() + "\n");

			this.enviarAlertaViaEmail(orgao, EstadoAcionamento.ATRASO);
			LOG.info("\nEnviadas Notificacoes sobre Acionamentos em Atraso - enviado "
					+ new Date() + "\n");

			this.enviarAlertaViaEmail(orgao, EstadoAcionamento.CRITICO);
			LOG.info("\nEnviadas Notificacoes sobre Acionamentos em Atraso Crítico - enviado "
					+ new Date() + "\n");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public boolean enviarAlertaViaEmail(Orgao orgao, EstadoAcionamento estado)
			throws DaoException {

		AcionamentoCtrl acionamentoCtrl = new AcionamentoCtrl(daoFactory);
		ParametrosGeraisCtrl parametrosCtrl = new ParametrosGeraisCtrl(
				daoFactory);

		String lsServidorSMTP = "";
		String lsDe = "";
		String lsPara = "";
		String lsAssunto = "";
		String lsTexto = "";
		String lsTextoParametrizado = "";

		List listaParametrosGerais = parametrosCtrl.list();

		ConfiguracoesOrgao configuracoes = orgao.getConfiguracoes();

		if (listaParametrosGerais != null) {

			lsServidorSMTP = ((ParametrosGerais) listaParametrosGerais.get(0))
					.getServidorSMTP();
		}

		lsDe = orgao.getConfiguracoes().getRemetenteEmail();

		lsAssunto = "Ouvidoria - " + orgao.getDescricao();

		Iterator listaAcionamentos = acionamentoCtrl
				.listaAcionamentosPorEstado(estado.getId()).iterator();

		/* Dados do acionamento */
		Acionamento acionamento = null;
		String enderecoUrlAcionamento = null;
		String estadoAcionamento = null;
		Timestamp dataAcionamento = null;
		Integer numeroProtocolo = null;
		String assunto = null;

		SimpleDateFormat data = null;
		String dataFormatada = null;

		String nomeFuncionarioResponsavel = null;
		String nomeOrgao = null;

		Funcionario funcionario = null;

		// Recupera o nome do acionador
		while (listaAcionamentos.hasNext()) {

			acionamento = (Acionamento) listaAcionamentos.next();

			funcionario = acionamento.getFuncionario();

			enderecoUrlAcionamento = urlDetalhamento(acionamento, orgao);
			estadoAcionamento = acionamento.getEstadoAcionamento()
					.getDescricao();
			dataAcionamento = acionamento.getDataAcionamento();
			numeroProtocolo = acionamento.getNumeroProtocolo();
			assunto = acionamento.getMensagem().getAssunto().getDescricao();

			data = new SimpleDateFormat("dd/MM/yyyy");
			dataFormatada = data.format(dataAcionamento);

			nomeFuncionarioResponsavel = funcionario.getNome();
			lsPara = funcionario.getEmail();

			nomeOrgao = orgao.getDescricao();

			if (lsPara != null && !lsPara.equals("")) {

				lsAssunto = "Ouvidoria - Notificação de Mensagem - Protocolo: "
						+ numeroProtocolo + " - Situação: " + estadoAcionamento;

				if (estado.getId() == EstadoAcionamento.PENDENTE.getId()) {
					lsTextoParametrizado = configuracoes
							.getTextoNotificacaoPendencia();

				} else if (estado.getId().equals(
						EstadoAcionamento.ATRASO.getId())) {
					lsTextoParametrizado = configuracoes
							.getTextoNotificacaoPendencia();

				} else if (estado.getId().equals(
						EstadoAcionamento.CRITICO.getId())) {
					lsTextoParametrizado = configuracoes
							.getTextoNotificacaoPendencia();
				}

				lsTexto = null;
				lsTexto = "<p>" + nomeFuncionarioResponsavel + "</b>,<br><br>"

				+ "<b>" + lsTextoParametrizado + "</b>"

				+ "<br><br><br>";

				lsTexto = lsTexto + "</p>";

				lsTexto += "<table  cellpadding='3' cellspacing='2' width='80%' border='0' bgcolor='#A9A9A9'>";

				lsTexto += "<tr style=' color: #0000FF; font-family:Verdana; font-size:10pt; font-weight:bold ;'>";
				lsTexto += "	 <td bgcolor='#DCDCDC'>Status</td>";
				lsTexto += "	  <td bgcolor='#DCDCDC'>Assunto </td>";
				lsTexto += "	  <td bgcolor='#DCDCDC'>N&uacute;mero </td>";
				lsTexto += "	  <td bgcolor='#DCDCDC'>Data Acionamento</td>";
				lsTexto += "	  <td bgcolor='#DCDCDC'>Respons&aacute;vel </td>";
				lsTexto += "</tr>";

				lsTexto += "<tr style=' color: #0000FF; font-family:Verdana; font-size:10pt; font-weight:bold ;text-decoration: none;'>";
				lsTexto += BG_COLOR_FFFFFF;
				lsTexto += "<a href='" + enderecoUrlAcionamento
						+ TARGET_BLANK + estadoAcionamento + "</a>";
				lsTexto += "     </td>";

				lsTexto += BG_COLOR_FFFFFF + "    	<a href='"
						+ enderecoUrlAcionamento + TARGET_BLANK
						+ assunto + "</a></td>";
				lsTexto += BG_COLOR_FFFFFF + "         <a href='"
						+ urlDetalhamento(acionamento, orgao)
						+ TARGET_BLANK + numeroProtocolo + "</a>"
						+ "      </td>";
				lsTexto += BG_COLOR_FFFFFF + "			<a href='"
						+ enderecoUrlAcionamento + TARGET_BLANK
						+ dataFormatada + "</a>" + "       </td>";
				lsTexto += BG_COLOR_FFFFFF + "			<a href='"
						+ enderecoUrlAcionamento + TARGET_BLANK
						+ nomeFuncionarioResponsavel + "</a>" + "       </td>";
				lsTexto += "</tr>";

				lsTexto += "</table>";

				lsTexto += "<br><br>";

				lsTexto += "Atenciosamente,<br><br>";
				lsTexto += "Ouvidoria - <b>" + nomeOrgao + "</b><br><br><br>";

				lsTexto += orgao.getConfiguracoes().getUrlSuporteUsuario();

				LOG.info("\n\nURL :" + enderecoUrlAcionamento + "\n");
				LOG.info("\nNumero protocolo :" + numeroProtocolo + "\n");
				LOG.info("\n>>>Responsavel :" + nomeFuncionarioResponsavel
						+ "\n");
				LOG.info("\n>>>Email de Destino :" + lsPara + "\n");
				LOG.info("\n>>>EstadoAcionamento :" + estadoAcionamento
						+ "\n\n");

				/* Eviar nota de alerta */
				Utilitario.enviarEmail(lsServidorSMTP, lsDe, lsPara, "", "",
						lsAssunto, lsTexto);

				LOG.info("\n>>>Enviado email para responsável pelo protocolo :"
						+ numeroProtocolo + "\n");

				acionamento = null;
				enderecoUrlAcionamento = null;
				estadoAcionamento = null;
				dataAcionamento = null;
				numeroProtocolo = null;
				assunto = null;

				data = null;
				dataFormatada = null;

				nomeFuncionarioResponsavel = null;
				lsPara = null;
				nomeOrgao = null;

				funcionario = null;
			}

		}
		// Monta o cabeçalho do email

		return true;
	}

	private String urlDetalhamento(Acionamento acionamento, Orgao orgao) {

		String lsEnderecoUrl = enderecoUrl;

		String enc = Utilitario.encrypt(Constants.DES_KEY, orgao.getId()
				.toString());

		lsEnderecoUrl = lsEnderecoUrl
				+ "/MainIntranet.do?path=/andamento/ExibeMsgPorRespons&id="
				+ acionamento.getId();
		lsEnderecoUrl = lsEnderecoUrl + "&osessionid=".concat(enc);

		return lsEnderecoUrl;
	}
}