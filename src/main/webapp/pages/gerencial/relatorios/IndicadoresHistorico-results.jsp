<%--
 Sistema de Ouvidoria: um canal atrav�s do qual os usu�rios
 podem encaminhar suas reclama��es, elogios e sugest�es.
 
 Copyright (C) 2011 SERPRO
 
 Este programa � software livre; voc� pode redistribu�-lo e/ou
 modific�-lo sob os termos da Licen�a P�blica Geral GNU, conforme
 publicada pela Free Software Foundation; tanto a vers�o 2 da
 Licen�a como (a seu crit�rio) qualquer vers�o mais nova.
 
 Este programa � distribu�do na expectativa de ser �til, mas SEM
 QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
 COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
 PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
 detalhes.
 
 Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU,
 sob o t�tulo "LICENCA.txt", junto com esse programa. Se n�o,
 acesse o Portal do Software P�blico Brasileiro no endere�o
 http://www.softwarepublico.gov.br/ ou escreva para a Funda��o do
 Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston,
 MA 02111-1301, USA.
 
 Contatos atrav�s do seguinte endere�o internet:
 http://www.serpro.gov.br/sistemaouvidoria/
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import="java.util.*" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/cewolf" prefix="cewolf" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/log-1.0" prefix="log" %>

<html:html lang="pt">

<%	String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<head>
	
	<%@ include file="inc/header.inc.jsp" %>
	<title>Indicadores</title>
	<link rel="stylesheet" type="text/css" media="all" href="../../<bean:write name="_LAYOUT_" property="esquemaCores"/>/estilo_conteudo.css"/>

	<script type="text/javascript" charset='iso-8859-1'>	
	<!-- 

		var indCaption = ['Total de Respostas Satisfat&oacute;rias', 'Tempo M&eacute;dio de Atendimento', 'Total de Atendimentos', 'Total de Atendimentos no Prazo']['<bean:write name='indicadoresHistoricoForm' property='sel_indicador'/>' - 1];
	
		function fillHeader() {
	
			var makeMetaVisible = <bean:write name='indicadoresHistoricoForm' property='sel_indicador'/> == 2;
		
			getRef('row_meio_envio').style.display = makeMetaVisible ? 'inline' : 'none';
			getRef('row_meta').style.display = makeMetaVisible ? 'inline' : 'none';
		
			var sel_tabela = <%= request.getParameter("sel_tabela") %>;
		
			getRef('r_modo_exibicao').innerHTML = modos_exibicao['cd'.indexOf('<bean:write name='indicadoresHistoricoForm' property='r_modo_exibicao'/>')];
			getRef('txt_periodo_i').innerHTML = '<bean:write name='indicadoresHistoricoForm' property='txt_periodo_i'/>';
			getRef('txt_periodo_f').innerHTML = '<bean:write name='indicadoresHistoricoForm' property='txt_periodo_f'/>';
			getRef('sel_tipo_grafico').innerHTML = tipos_grafico['<bean:write name='indicadoresHistoricoForm' property='sel_tipo_grafico'/>'];
			getRef('txt_meta').innerHTML = '<bean:write name='indicadoresHistoricoForm' property='txt_meta'/>';
			getRef('sel_indicador').innerHTML = indCaption;
			getRef('sel_agrupamento').innerHTML = agrupamento.fromChar('<bean:write name='indicadoresHistoricoForm' property='sel_agrupamento'/>');
			getRef('sol').innerHTML = '<%= br.gov.serpro.ouvidoria.util.ReportKit.getLocationCaptions(request.getParameter("h_locais")) %>';
		
			getRef('img_grafico').title = "Gr�fico com os �ndices de " + indCaption + " da Ouvidoria";
	
		}
	
		function tabelaChanged(el) {
			var target = el.form.sel_itens_tabela.options;
			var tableIndex = el.options[el.selectedIndex].value;
			populateOptions(tableData[tableIndex], target, true);
		}
	
		function submitIt(frm) {
			frm.submit();
		}

		function onLoad() {
			try { document.getElementById("titulo").focus(); } catch(e) {}
		}

	// -->
	</script>

	<!-- IN�CIO DOS SCRIPTS UTILIZADOS NO ENVIO E IMPRESS�O-->
	<script src="../../js/util.js"></script>
	<script type="text/javascript" charset='iso-8859-1'>
	<!-- //
		function imprimir() {
			win = novaJanela("","Boletim", 750, 570, "yes","no", 10, 10 );
			win.document.write(montaHtmlImpressaoIndicadores("<%= contextPath %>", "<bean:write name="_LAYOUT_" property="esquemaCores"/>", document.getElementById("tab_conteudo").innerHTML));
			win.document.close();
			win.print();
		}
		
		function enviar() {
			document.forms["frmHtml"].elements["htmlRelatorio"].value = document.getElementById("tab_conteudo").innerHTML;
			document.forms["frmHtml"].submit();
		}
	// -->
	</script>
	<!-- FIM DOS SCRIPTS UTILIZADOS NO ENVIO E IMPRESS�O-->

</head>

<body onload="onLoad();">




<html:form action="/IndicadoresHistoricoSearch" method="post">

<!-- IN�CIO DA �REA DO RELAT�RIO -->
<div id="tab_conteudo">

<input type='hidden' name='op' />

	<!-- IN�CIO DA �REA DE CABE�ALHO -->
	<DIV id="tab_cabecalho">

	<TABLE cellspacing="1" cellpadding="2" width="100%" border="0">
	<TBODY>
		<TR>
			<TD class=contentBold align=middle>&nbsp;<label tabindex="1" id="titulo">Indicador de Hist&oacute;rico da Ouvidoria</label></TD>
		</TR>
	</TBODY>
	</TABLE>

	<TABLE cellSpacing=1 cellPadding=2 width="100%" border=0>
	<TBODY>
	<TR>
    	<TD class=contentBold colSpan=2>&nbsp;<label tabindex="2">Par&acirc;metros Utilizados para Gerar o Indicador:</label></TD>
	</tr>

	<%@ include file="IndicadoresHistorico-results-params.inc.jsp" %>

	</TBODY>
	</TABLE>
	</DIV>
	<!-- FIM DA �REA DE CABE�ALHO -->

	<!-- IN�CIO DA �REA DO GR�FICO -->
	<DIV id="tab_grafico">
	<TABLE cellspacing="1" cellpadding="2" width="100%" border="0">
	<TBODY>
		<TR>
    		<TD align=middle>

			<%-- Cewolf image --%>
			<jsp:useBean id="pieData" class="br.gov.serpro.ouvidoria.util.PieData" scope="page"  />
			<jsp:useBean id="myEnhancer" class="br.gov.serpro.ouvidoria.util.PlotEnhancer" scope="page" />
			<jsp:useBean id="params" class="br.gov.serpro.ouvidoria.util.ParamBean" scope="request"/>
			<jsp:setProperty name="params" property="*"/>
			
			<%@ include file="IndicadoresHistorico-chart.inc.jsp" %>

			<img tabindex="4" name="img_grafico" alt="Gr&aacute;fico com os &Iacute;ndices de Satisfa&ccedil;&atilde;o da Ouvidoria" border="0" width="600" height="320" src="<%= pageContext.getAttribute("imgURL") %>"/>

			</TD>
		</TR>
	</TBODY>
	</TABLE>

	</DIV>
	<!-- FIM DA �REA DO GR�FICO -->

	<!-- IN�CIO DA �REA DA TABELA DE DADOS -->
	<DIV id="tab_dados">
	<TABLE cellspacing="1" cellpadding="2" width="100%" border="0">
	<TBODY>
	<%
	org.apache.struts.action.DynaActionForm frm = (org.apache.struts.action.DynaActionForm) request.getAttribute("indicadoresHistoricoForm");
	String selIndicador = frm.getString("sel_indicador");

	if ("1".indexOf(selIndicador) >= 0) {
	%>
		<%@ include file="IndicadoresHistorico-records-1.inc.jsp" %>
	<%} else if ("4".indexOf(selIndicador) >= 0) {%>
		<%@ include file="IndicadoresHistorico-records-4.inc.jsp" %>
	<%} else {%>
		<%@ include file="IndicadoresHistorico-records.inc.jsp" %>
	<%}%>
	</TBODY>
	</TABLE>
	</DIV>
	<!-- FIM DA �REA DA TABELA DE DADOS -->

</div>
<!-- FIM DA �REA D0 RELAT�RIO -->
</html:form>

<!-- IN�CIO DA �REA DE A��ES DO USU�RIO -->
<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
	<TBODY>
	<TR>
	<TD align="middle">
   		<A tabindex="6" title="Imprimir" href="javascript:imprimir()"><IMG title=Imprimir src="../../images/impressora.jpg" border=0></A>&nbsp;&nbsp;
		<A tabindex="6" title="Imprimir" href="javascript:imprimir()">IMPRIMIR</A>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<A tabindex="6" title="Gerar boletim" href="javascript:enviar();"><IMG title="Gerar Boletim" src="../../images/formulario.jpeg" border=0></A> 
		<A tabindex="6" title="Gerar boletim" href="javascript:enviar();">GERAR BOLETIM</A> 
	</TD>
	</TR>
	</TBODY>
</TABLE>
<!-- FIM DA �REA DE A��ES DO USU�RIO -->

<!-- IN�CIO DO FORMUL�RIO PARA ENVIAR O HTML DA PÁGINA -->
<form name="frmHtml" action="../../../aprendizado/GerarBoletim.do" method="post">
	<input type="hidden" name="htmlRelatorio">
	<input type="hidden" name="imgId" value="<%= pageContext.getAttribute("imgURL") %>">
</form>
<!-- FIM DO FORMUL�RIO PARA ENVIAR O HTML DA PÁGINA -->
<script type="text/javascript" charset='iso-8859-1'>
fillHeader();
</script>
</body>
</html:html>	