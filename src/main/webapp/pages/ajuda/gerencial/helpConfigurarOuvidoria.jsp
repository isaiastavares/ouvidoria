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
<HTML>

<head>
<link rel="stylesheet"  media="all" href="../estilos/estilo_ajuda.css" type="text/css">
<link rel="stylesheet"  media="all" href="../../estilos/Azul/estilo_conteudo.css" type="text/css">
<script language='javascript'>

function onLoad(){
	try { document.getElementById("titulo").focus() } catch(e) {};
}
</script>
</head>		

	
<body onload="javascript:onLoad();" style="margin:25px 2.8em">
<table border="0" cellpadding="0" cellspacing="0" width="50%" align="center">
<tr>
 <td align="center" class="titulo" align="center">
 	<label tabindex=1 id="titulo"><b>Configurar Ouvidoria</b></label>
 </td>
</tr>
<tr>
 <td align="center"><br>
 	<img src="../../images/ajuda/configurarOuvidoria_1.jpg" tabindex="2" name="imgTela" id="imgTela" alt="Imagem da tela administrar usu�rio">
 	<img src="../../images/ajuda/configurarOuvidoria_2.jpg" tabindex="3" name="imgTela" id="imgTela" alt="Imagem da tela administrar usu�rio"> 	
 	<img src="../../images/ajuda/configurarOuvidoria_3.jpg" tabindex="3" name="imgTela" id="imgTela" alt="Imagem da tela administrar usu�rio"> 	
 </td>
</tr>	
<tr>
 <td>
 	<p align="center" class="obrigatorio" tabindex="4"><b>OBS.: O padr�o de cores &eacute; meramente ilustrativo.</b></p>
 </td>
</tr>
 <tr><td><br><br></td></tr> 
 <tr>
   <td>
   	<b><label tabindex="5">Funcionalidade</label></b>
   </td>
 </tr>
 <tr>
   <td>
   <DD><label tabindex="6">
   		Permite que os dados de configura��o da ouvidoria sejam atualizados.
	</label></DD>   
   </td>
 </tr> 
 <tr>
  	<td></td>
 </tr>
 <tr><td><br><br></td></tr> 
<tr>
 <td>  
	  
	  <table border="0" cellpadding="5" cellspacing="0" align="center" width="100%">		
		<tr>
			<td height="30" colspan="2" valign="top">
				<a name="detalhe"><label tabindex="7"><b>1 - Detalhamento do Script</b></label></a>
			</td>
		</tr>
		<tr>
			<td height="40" colspan="2" valign="top">
				<label tabindex="8">Neste espa�o realizam-se opera��es sobre a Ouvidoria corrente. As opera��es poss�veis s�o:</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="150" valign="top">
				<DD><label tabindex="9"><b>Alterar</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="10">Alterar os dados da Ouvidoria.</label>
			</td>
		</tr>
		<tr>
			<td height="40" colspan="2" valign="top">
				<label tabindex="11">Os dados dispon�veis para altera��o s�o:</label>
			</td>
		</tr>
		
		<tr>
			<td height="40" colspan="2" valign="top">
				<label tabindex="12">1.1 - Configura��es de layout</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="13"><b>Diret�rio de esquema de cores</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="14">Campo para sele��o do padr�o de cores do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="15"><b>Nome do �rg�o para exibi��o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="16">Campo para digita��o do nome do �rg�o que ser� apresentado no sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="17"><b>Imagem do logo do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="18">Campo para inser��o de uma imagem que ser� exibida no canto superior esquerdo do frame superior do sistema. Essa imagem deve ter, preferencialmente, 20 pixels de altura.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="19"><b>Descri��o para a imagem do logo do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="20">Campo para digita��o da descri��o da imagem exibida no canto superior esquerdo do frame superior do sistema. Essa descri��o que ser� lida pro programas que auxiliam deficientes visuais a acessarem o sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="21"><b>Link para a imagem do logo do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="22">Campo para digita��o do link da imagem exibida no canto superior esquerdo do frame superior do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="23"><b>Cor referente � p�gina do logo</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="24">Campo para digita��o no padr�o RGB da cor de fundo da barra superior do frame superior do sistema. A cor digitada ser� mostrada ao lado do campo de digita��o.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="25"><b>Imagem do banner do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="26">Campo para inser��o de uma imagem que ser� exibida no frame superior do sistema. Essa imagem deve ter, preferencialmente, 60 pixels de altura.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="27"><b>Descri��o para a imagem do banner do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="28">Campo para digita��o da descri��o da imagem exibida no frame superior do sistema. Essa descri��o que ser� lida pro programas que auxiliam deficientes visuais a acessarem o sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="29"><b>Link para a imagem do banner do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="30">Campo para digita��o do link da imagem exibida frame superior do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="31"><b>Cor referente a p�gina do banner</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="32">Campo para digita��o no padr�o RGB da cor de fundo do frame superior do sistema. A cor digitada ser� mostrada ao lado do campo de digita��o.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="33"><b>Imagem do canto superior a direita</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="34">Campo para inser��o de uma imagem que ser� exibida no canto superior direito do frame superior do sistema. Essa imagem deve ter, preferencialmente, 20 pixels de altura.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="35"><b>Descri��o para a imagem do canto superior a direita</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="36">Campo para digita��o da descri��o da imagem exibida no canto superior direito do frame superior do sistema. Essa descri��o que ser� lida pro programas que auxiliam deficientes visuais a acessarem o sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="37"><b>Link para a imagem do canto superior a direita</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="38">Campo para digita��o do link da imagem exibida no canto superior direito do frame superior do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="39"><b>Outra imagem para layout</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="40">Campo para inser��o de uma imagem auxiliar ao sistema</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="41"><b>Descri��o para a outra imagem para layout</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="42">Campo para digita��o da descri��o da imagem auxiliar do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="43"><b>Link para a outra imagem para layout</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="44">Campo para digita��o do link da imagem auxiliar do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="45"><b>Descri��o para a outra imagem para layout</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="46">Campo para digita��o da descri��o da imagem auxiliar do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="47"><b>Link para a outra imagem para layout</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="48">Campo para digita��o do link da imagem auxiliar do sistema.</label>
			</td>
		</tr>
		<tr>
			<td height="40" colspan="2" valign="top">
				<label tabindex="49">1.2 - Textos configur�veis</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="50"><b>Texto de apresenta��o da ouvidoria</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="51">Campo para digita��o do texto de apresenta��o da ouvidoria, exibido na p�gina de entrada do sistema.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="52"><b>Texto de apresenta��o do acionamento</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="53">Campo para digita��o do texto de apresenta��o do formul�rio de acionamento da ouvidoria, exibido acima do formul�rio.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="54"><b>Texto de resposta no momento do acionamento</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="55">Campo para digita��o do texto de confirma��o do envio do acionamento. Juntamente ao texto ser�o exibidos o protocolo e o c�digo de acesso (quando houver).</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="56"><b>Texto consulta de resposta no prazo</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="">Campo para digita��o do texto de consulta da resposta que se encontra dentro do prazo pr&eacute;-estipulado pela ouvidoria e que ainda n�o foi respondida.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="57"><b>Texto consulta de resposta sem prazo</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="58">Campo para digita��o do texto de consulta da resposta que n�o possui prazo pr&eacute;-estipulado pela ouvidoria e que ainda n�o foi respondida.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="59"><b>Texto consulta de resposta em atraso</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="60">Campo para digita��o do texto de consulta da resposta que j� ultrapassou o prazo pr&eacute;-estipulado pela ouvidoria, mas que ainda n�o foi respondida.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="61"><b>Texto apresenta��o da resposta</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="62">Campo para digita��o do texto de apresenta��o da resposta da ouvidoria. Ser� exibido acima da resposta.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="63"><b>Texto de avalia��o da resposta</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="64">Campo para digita��o do texto de confirma��o do envio da avalia��o da resposta dada ao acionamento.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="65"><b>Texto com informa��es sobre a ouvidoria</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="66">Campo para digita��o do texto com informa��es gerais sobre a ouvidoria (por exemplo: hist�rico, objetivos etc) que ser�o divulgadas para o p&uacute;blico.</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="67"><b>Texto referente a outros canais de comunica��o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="68">Campo para digita��o do texto apresenta��o dos outros canais de comunica��o da ouvidoria (por exemplo: e-mail, telefone, fax, endere�o etc).</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="69"><b>Texto referente a legisla��o do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="70">Campo para digita��o do texto com legisla��o relativa � ouvidoria que ser� divulgada para o p&uacute;blico geral.</label>
			</td>
		</tr>	
		<tr>
			<td height="40" colspan="2" valign="top">
				<label tabindex="71">1.3 - Par�metros de Configura��o</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="72"><b>�rg�o permite anexar arquivo ao acionamento</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="73">Campo para permitir que sejam anexados arquivos aos acionamentos realizados na ouvidoria. </label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="74"><b>Tamanho m�ximo para arquivos anexos (em KB)</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="75">Campo para digita��o do tamanho m�ximo dos arquivos anexados aos acionamentos realizados na ouvidoria.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="76"><b>�rg�o permite mensagem digitalizada no acionamento</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="77">Campo para permitir que sejam anexadas mensagens digitalizadas aos acionamentos realizados na ouvidoria. </label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="78"><b>Tamanho m�ximo para carta digitalizada (em KB)</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="79">Campo para digita��o do tamanho m�ximo das mensagens digitalizadas anexadas aos acionamentos realizados na ouvidoria.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="80"><b>�rg�o possui c�digo de acesso</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="81">Campo para permitir que a ouvidoria forne�a c�digos de acesso a todos os acionamentos realizados na mesma. Para consultar a resposta aos acionamentos ser� necess�rio que o usu�rio informe esse c�digo de acesso, al&eacute;m do protocolo.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="82"><b>Existe bloqueio por falhas na digita��o do c�digo de acesso</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="83">Campo para permitir que haja bloqueio ao protocolo caso o c�digo de acesso seja digitado errado. </label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="84"><b>N&uacute;mero m�ximo de falhas no c�digo de acesso</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="85">Campo para digita��o do n&uacute;mero de vezes que um c�digo de acesso deve ser digitado errado para que o protocolo seja bloqueado.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="86"><b>Tempo de bloqueio pelo c�digo de acesso (em minutos)</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="87">Campo para digita��o do tempo que o protocolo ficar� bloqueado protocolo caso o c�digo de acesso seja digitado errado.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="88"><b>Existe bloqueio por falhas na digita��o da pergunta para recupera��o do c�digo de acesso</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="89">Campo para permitir que haja bloqueio ao protocolo caso a resposta para recupera��o do c�digo de acesso seja digitada errada.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="90"><b>N&uacute;mero m�ximo de falhas na pergunta</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="91">Campo para digita��o do n&uacute;mero de vezes que a resposta para recupera��o do c�digo de acesso deve ser digitada errada para que o protocolo seja bloqueado.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="92"><b>Tempo de bloqueio pela pergunta (em minutos)</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="93">Campo para digita��o do tempo que o protocolo ficar� bloqueado protocolo caso a resposta para recupera��o do c�digo de acesso seja digitada errada.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="94"><b>Atendente pode consultar mensagens durante o atendimento</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="95">Campo para permitir que os atendentes da ouvidoria consultem mensagens quando estiverem realizando atendimentos.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="96"><b>&eacute; exigido certificado digital para acesso ao �rg�o e a todos os sub-�rg�os</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="97">Campo para exigir que a identifica��o no sistema seja feita atrav&eacute;s de certificado digital, tanto para o �rg�o quanto para os sub-�rg�os.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="98"><b>&eacute; exigido certificado digital para acesso ao �rg�o e opcional para todos os sub-�rg�os</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="99">Campo para exigir que a identifica��o no sistema seja feita atrav&eacute;s de certificado digital para o �rg�o e opcionalmente para os sub-�rg�os.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="100"><b>Nome do diret�rio do �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="101">Nome do diret�rio do �rg�o, que comp�e a URL da ouvidoria. </label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="102"><b>URL para consulta de funcion�rios</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="103">Campo para digita��o da URL para consulta dos dados cadastrais dos funcion�rios do �rg�o.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="104"><b>URL de suporte ao usu�rio</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="105">Campo para digita��o da URL de suporte ao usu�rio.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="106"><b>Remetente dos e-mails que ser�o enviados pela aplica��o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="107">Campo para digita��o do remetente dos e-mails que ser�o enviados pela ouvidoria. </label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="108"><b>Tipos de acionador permitidos para o �rg�o</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="109">Campos para sele��o dos tipos de usu�rio que ser�o aceitos pelo �rg�o.</label>
			</td>
		</tr>	
		<tr>
			<td align="left" height="40" width="350" valign="top">
				<DD><label tabindex="110"><b>Meios de envio de resposta permitidos para o �rg�o:</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="111">Campos para sele��o dos meios de envio de reposta que estar�o dispon�veis para sele��o pelo acionador.</label>
			</td>
		</tr>	
		<tr>				
			<td height="40" colspan="2" valign="top">
				<label tabindex="112">Estao dispon�veis os bot�es que operam sobre a Ouvidoria:</label>
			</td>
		</tr>
		<tr>
			<td align="left" height="40" width="150" valign="top">
				<DD><label tabindex="113"><b>Alterar</b></label></DD>
			</td>
			<td valign="top">
				<label tabindex="114">Alterar os dados da Ouvidoria</label>
			</td>			
		</tr>	
	  </table>	  
	  	  
	</td>
	</tr>
	<tr>
	 <td>
		<hr>
		  <p class="voltar"><A title="imprimir esta p�gina" href="javascript:window.print()" tabindex="115">imprimir</A> 
		    . <A title="topo da p�gina" href="javascript:window.location=location.href;" tabindex="116">topo</A>
	 </td>
	</tr>
</table> 	    

	
</body>	

</HTML>