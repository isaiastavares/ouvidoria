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

/**
 * @refatorado gilmar.junior
 * @since 2016.11.20
 * */
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

	@SuppressWarnings("rawtypes")
	public boolean enviarAlertaViaEmail(Orgao orgao, EstadoAcionamento estado)
			throws DaoException {

		AcionamentoCtrl acionamentoCtrl = new AcionamentoCtrl(daoFactory);
		ParametrosGeraisCtrl parametrosCtrl = new ParametrosGeraisCtrl(
				daoFactory);

		String lsServidorSMTP = "";
		String lsTextoParametrizado = "";
		
		List listaParametrosGerais = parametrosCtrl.list();

		ConfiguracoesOrgao configuracoes = orgao.getConfiguracoes();

		if (listaParametrosGerais != null) {

			lsServidorSMTP = ((ParametrosGerais) listaParametrosGerais.get(0))
					.getServidorSMTP();
		}

		String lsDe = orgao.getConfiguracoes().getRemetenteEmail();

		String lsAssunto = "Ouvidoria - ".concat(orgao.getDescricao());

		Iterator listaAcionamentos = acionamentoCtrl.listaAcionamentosPorEstado(estado.getId()).iterator();

		// Recupera o nome do acionador
		while (listaAcionamentos.hasNext()) {

			Acionamento acionamento = (Acionamento) listaAcionamentos.next();

			Funcionario funcionario = acionamento.getFuncionario();

			String enderecoUrlAcionamento = urlDetalhamento(acionamento, orgao);
			String estadoAcionamento = acionamento.getEstadoAcionamento().getDescricao();
			Timestamp dataAcionamento = acionamento.getDataAcionamento();
			Integer numeroProtocolo = acionamento.getNumeroProtocolo();
			String assunto = acionamento.getMensagem().getAssunto().getDescricao();

			SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormatada = data.format(dataAcionamento);

			String nomeFuncionarioResponsavel = funcionario.getNome();
			String lsPara = funcionario.getEmail();

			String nomeOrgao = orgao.getDescricao();

			if (lsPara != null && !lsPara.isEmpty()) {

				StringBuilder sbAssunto = new StringBuilder();
				sbAssunto.append("Ouvidoria - Notificação de Mensagem - Protocolo: ").append(numeroProtocolo).append(" - Situação: ").append(estadoAcionamento);
				lsAssunto = sbAssunto.toString();

				if (estado.getId() == EstadoAcionamento.PENDENTE.getId()) {
					lsTextoParametrizado = configuracoes.getTextoNotificacaoPendencia();

				} else if (estado.getId().equals(EstadoAcionamento.ATRASO.getId())) {
					lsTextoParametrizado = configuracoes.getTextoNotificacaoPendencia();

				} else if (estado.getId().equals(EstadoAcionamento.CRITICO.getId())) {
					lsTextoParametrizado = configuracoes.getTextoNotificacaoPendencia();
				}

				StringBuilder lsTexto = new StringBuilder();
				lsTexto.append("<p>").append(nomeFuncionarioResponsavel).append("</b>,<br><br>");
				lsTexto.append("<b>").append(lsTextoParametrizado).append("</b>");
				lsTexto.append("<br><br><br>");
				lsTexto.append("</p>");
				lsTexto.append("<table  cellpadding='3' cellspacing='2' width='80%' border='0' bgcolor='#A9A9A9'>");

				lsTexto.append("<tr style=' color: #0000FF; font-family:Verdana; font-size:10pt; font-weight:bold ;'>");
				lsTexto.append("	 <td bgcolor='#DCDCDC'>Status</td>");
				lsTexto.append("	  <td bgcolor='#DCDCDC'>Assunto </td>");
				lsTexto.append("	  <td bgcolor='#DCDCDC'>N&uacute;mero </td>");
				lsTexto.append("	  <td bgcolor='#DCDCDC'>Data Acionamento</td>");
				lsTexto.append("	  <td bgcolor='#DCDCDC'>Respons&aacute;vel </td>");
				lsTexto.append("</tr>");

				lsTexto.append("<tr style=' color: #0000FF; font-family:Verdana; font-size:10pt; font-weight:bold ;text-decoration: none;'>");
				lsTexto.append(BG_COLOR_FFFFFF);
				lsTexto.append("<a href='").append(enderecoUrlAcionamento).append(TARGET_BLANK).append(estadoAcionamento).append("</a>");
				lsTexto.append("     </td>");

				lsTexto.append(BG_COLOR_FFFFFF).append("    	<a href='").append(enderecoUrlAcionamento).append(TARGET_BLANK);
				lsTexto.append(assunto).append("</a></td>");
				lsTexto.append(BG_COLOR_FFFFFF).append("         <a href='").append(urlDetalhamento(acionamento, orgao)).append(TARGET_BLANK);
				lsTexto.append(numeroProtocolo).append("</a>");
				lsTexto.append("      </td>");
				lsTexto.append(BG_COLOR_FFFFFF).append("			<a href='").append(enderecoUrlAcionamento).append(TARGET_BLANK).append(getClass());
				lsTexto.append(dataFormatada).append("</a> </td>");
				lsTexto.append(BG_COLOR_FFFFFF).append("			<a href='").append(enderecoUrlAcionamento).append(TARGET_BLANK);
				lsTexto.append(nomeFuncionarioResponsavel).append("</a> </td>");
				lsTexto.append("</tr>");
				lsTexto.append("</table>");
				lsTexto.append("<br><br>");
				lsTexto.append("Atenciosamente,<br><br>");
				lsTexto.append("Ouvidoria - <b>").append(nomeOrgao).append("</b><br><br><br>");
				lsTexto.append(orgao.getConfiguracoes().getUrlSuporteUsuario());

				//criando String de log
				StringBuilder logInfo = new StringBuilder();
				logInfo.append("\n\nURL :").append(enderecoUrlAcionamento).append("\n");
				logInfo.append("\nNumero protocolo :").append(numeroProtocolo).append("\n");
				logInfo.append("\n>>>Responsavel :").append(nomeFuncionarioResponsavel).append("\n");
				logInfo.append("\n>>>Email de Destino :").append(lsPara).append("\n");
				logInfo.append("\n>>>EstadoAcionamento :").append(estadoAcionamento).append("\n\n");

				/* Eviar nota de alerta */
				Utilitario.enviarEmail(lsServidorSMTP, lsDe, lsPara, "", "",
						lsAssunto, lsTexto.toString());

				logInfo.append("\n>>>Enviado email para responsável pelo protocolo :").append(numeroProtocolo).append("\n");
				
				LOG.info(logInfo.toString());
			}

		}
		// Monta o cabeçalho do email

		return true;
	}

	private String urlDetalhamento(Acionamento acionamento, Orgao orgao) {
		StringBuilder sbLsEnderecoUrl = new StringBuilder();
		sbLsEnderecoUrl.append(enderecoUrl);

		String enc = Utilitario.encrypt(Constants.DES_KEY, orgao.getId().toString());

		sbLsEnderecoUrl.append("/MainIntranet.do?path=/andamento/ExibeMsgPorRespons&id=").append(acionamento.getId());
		sbLsEnderecoUrl.append("&osessionid=".concat(enc));

		return sbLsEnderecoUrl.toString();
	}
}