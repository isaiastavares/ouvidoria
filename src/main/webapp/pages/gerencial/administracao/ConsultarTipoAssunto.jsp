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

<% /*
   -- Sistema:      Ouvidoria
   -- M�dulo:       Gerencial
   -- Aplica��o:    Gestor de Sistema
   -- 
   -- Vers�o:       1.0
   -- Data:         03/01/2005  
   -- 
  */
%>

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<html lang="pt">
	<head>
		<title>Manter Tipos de Assunto</title>
		<html:base />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet"  media="all" href="../../<bean:write name="_LAYOUT_" property="esquemaCores"/>/estilo_conteudo.css" type="text/css">			
<script type="text/javascript" charset='iso-8859-1'>
 function onLoad(){
  document.getElementById("titulo").focus();
 }
</script>

	</head>


	<body onload="onLoad();">


		<div id="content">
			<h1 id="titulo" title="manter tabela de assuntos de mensagens" tabindex="1">Manter tabela de Assuntos de Mensagens</h1>
			<table cellpadding="0" cellspacing="0" border="0" width="100%">

				<tr>
					<td>
						<iframe tabindex="2" src="../../../gerencial/AtualizarTipoAssunto.do?action=listar" tabindex="11" id="lista" name="lista" longdesc="listagem" width="100%" height="200" scrolling="0" marginwidth="0" marginheight="0" frameborder="0"></iframe>
					</td>
				</tr>

				<tr>
					<td height="4">
					</td>
				</tr>

				<tr>
					<td>
						<iframe tabindex="3" src='../../../gerencial/Vazio.do' tabindex="11" id="detalhe" name="detalhe" longdesc="detalhe" width="100%" height="0" scrolling="0" marginwidth="0" marginheight="0" frameborder="0"></iframe>
					</td>
				</tr>
			</table>
			<hr>
			<p class="voltar"> 
				<a tabindex="4" title="voltar para p�gina anterior" href="javascript:history.go(-1)" >voltar</a>
			</p>

		</div>
	</body>
</html>
