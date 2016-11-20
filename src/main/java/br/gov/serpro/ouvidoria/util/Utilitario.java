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
package br.gov.serpro.ouvidoria.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import br.gov.serpro.ouvidoria.controller.gerencial.administracao.ParametrosGeraisCtrl;
import br.gov.serpro.ouvidoria.dao.DaoException;
import br.gov.serpro.ouvidoria.dao.DaoFactory;
import br.gov.serpro.ouvidoria.model.ParametrosGerais;

/**
 *
 * @author SERPRO
 * @version $Revision: 1.1.2.3 $, $Date: 2011/10/20 15:30:35 $
 * @version 0.1, Date: 2005/01/28
 */
public final class Utilitario {

    private static final String CIPHER_ERROR_2 = "Cipher Error";
	private static final String CIPHER_ERROR = "Cipher Error: ";

	private Utilitario() {
    }

    /**
     * Este método auxiliar retorna o número de milisegundos em uma quantidade
     * de dias.
     *
     * @param numDias
     *            número de dias
     * @return número de milisegundos em numDias dias
     */
    public static long converterDiaMilisegundos(int numDias) {

        return ((long)(numDias)) * 86400000;

   }

    /**
     * Este método auxiliar retorna o número de milisegundos de uma quantidade
     * de minutos.
     *
     * @param numMinutos
     *            número de minutos
     * @return número de milisegundos em numMinutos minutos
     */
    public static long converterMinutoMilisegundos(int numMinutos) {
        //60000 = 60 * 1000 (numSegundos * 1000)
        return numMinutos * 60000;
    }

    /**
     * @param valorBytes
     *            número de bytes
     * @return número de kbytes
     */
    public static int conveterBytesKBytes(int valorBytes) {
        return valorBytes / 1024;
    }

    /**
     * Este método é responsável por gerar uma string randômica composta por
     * números ou letras (maiúsculas e minúsculas).
     *
     * @param tamanho
     *            O tamanho da string randômica a ser gerada.
     * @return Retorna uma String randômica de tamanho 'tamanho'.
     */
    public static String gerarStringRandomica(int tamanho) {
        Random rand = new Random();
        StringBuffer buf = new StringBuffer();
        String allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int charCount = allowedChars.length();
        int index;
        for (int count = 0; count < tamanho; count++) {
            index = rand.nextInt(charCount);
            buf.append(allowedChars.charAt(index));
        }
        return buf.toString();
    }

    /**
     * Este método é responsável por gerar uma string randômica composta por
     * números.
     *
     * @param tamanho
     *            O tamanho da string randômica a ser gerada.
     * @return Retorna uma String randômica de tamanho 'tamanho'.
     */
    public static String gerarNumeroRandomico(Integer tamanho) {
        Random rand = new Random();
        StringBuffer buf = new StringBuffer();
        String allowedChars = "0123456789";
        int charCount = allowedChars.length();
        int index;
        for (int count = 0; count < tamanho.intValue(); count++) {
            index = rand.nextInt(charCount);
            buf.append(allowedChars.charAt(index));
        }
        return buf.toString();
    }

    /**
     * Este método é responsável por validar um e-mail
     *
     * @param e
     *            O e-mail a ser validado
     * @return Retorna true se o e-mail é válido, false caso contrário
     */
    public static boolean validaEmail(final String e) {
        int arroba;
        int ponto;
        String stringAntesArroba;
        String stringDepoisArroba1;
        String stringDepoisArroba2;
        String email = e;

        arroba = email.indexOf("@");

        if (arroba == -1)
            return false;
        stringAntesArroba = email.substring(0, arroba);
        if (stringAntesArroba.length() <= 0) {
            return false;
        }
        email = email.substring(arroba + 1);
        ponto = email.indexOf(".");

        if (ponto == -1)
            return false;
        stringDepoisArroba1 = email.substring(0, ponto);
        if (stringDepoisArroba1.length() <= 0) {
            return false;
        }
        stringDepoisArroba2 = email.substring(ponto + 1);
        if (stringDepoisArroba2.length() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Este método é responsável por validar um cpf
     *
     * @param cpf
     *            O cpf a ser validado
     * @return Retorna true se o cpf é válido, false caso contrário
     */
    public static boolean validaCpf(String cpf) {
        int i;

        if (cpf.length() != 11)
            return false;

        String c = cpf.substring(0, 9);
        String dv = cpf.substring(9);
        int d1 = 0;

        for (i = 0; i < 9; i++) {
            d1 += Integer.parseInt(c.substring(i, i + 1)) * (10 - i);
        }

        if (d1 == 0) {
            return false;
        }

        d1 = 11 - (d1 % 11);

        if (d1 > 9)
            d1 = 0;

        if (Integer.parseInt(dv.substring(0, 1)) != d1) {
            return false;
        }

        d1 *= 2;

        for (i = 0; i < 9; i++) {

            d1 += Integer.parseInt(c.substring(i, i + 1)) * (11 - i);
        }

        d1 = 11 - (d1 % 11);

        if (d1 > 9)
            d1 = 0;

        if (Integer.parseInt(dv.substring(1, 2)) != d1) {
            return false;
        }
        return true;
    }

    /**
     * Este método é responsável por enviar email.
     *
     * @param pServidorSMTP
     * @param pDe
     * @param pPara
     * @param pCopia
     * @param pBcc
     * @param pAssunto
     * @param pTexto
     *
     * @return true se o email for enviado, false caso contrário.
     */
    public static boolean enviarEmail(final String pServidorSMTP, final String pDe,
            final String pPara, final String pCopia, final String pBcc, final String pAssunto,
            final String pTexto) {

        Properties mailPprops = new Properties();
        mailPprops.put("mail.smtp.host", pServidorSMTP);

        Session mailSession = Session.getDefaultInstance(mailPprops, null);

        try {
            //Mudança: Aplicação usa ";", componente usa ","
            String para = pPara.replaceAll(";", ",");

            // Criando a mensagem
            MimeMessage msg = new MimeMessage(mailSession);
            // Atribuir rementente
            msg.setFrom(new InternetAddress(pDe));
            // Atribuir destinatários
            InternetAddress[] endereco = null;
            // Para
            if ((para != null) && (!para.equals(""))) {
                endereco = InternetAddress.parse(para);
                msg.setRecipients(Message.RecipientType.TO, endereco);
            }
            // Cc
            if ((pCopia != null) && (!pCopia.equals(""))) {
                endereco = InternetAddress.parse(pCopia);
                msg.setRecipients(Message.RecipientType.CC, endereco);
            }
            // Bcc
            if ((pBcc != null) && (!pBcc.equals(""))) {
                endereco = InternetAddress.parse(pBcc);
                msg.setRecipients(Message.RecipientType.BCC, endereco);
            }

            // Atribuir assunto
            msg.setSubject(pAssunto);

            // Atribuir corpo do email (texto)
            if (pTexto != null)
                msg.setContent(pTexto, "text/html");

            msg.setSentDate(new Date());

            Transport.send(msg);

            msg = null;
            mailSession = null;
        } catch (MessagingException mex) {
            if (Constants.DEBUG) {
                mex.printStackTrace(System.out);
            }
            return false;
        }
        return true;
    }

    /**
     * Converte um Array de Byte em uma String de Hex
     *
     * @param Array
     *            de Bytes
     * @return String Hex
     */
    private static String byteToHex(byte b[]) {
        String retorno = new String();
        for (int j = 0; j < b.length; j++) {
            int i = b[j] & 0xFF;
            if (i < 16) {
                retorno += "0" + (Integer.toHexString(i)).toUpperCase();
            } else {
                retorno += (Integer.toHexString(i)).toUpperCase();
            }
        }
        return retorno;
    }

    /**
     * Converte uma String Hex em um Array de Byte
     *
     * @param String
     *            Hex
     * @return Array de Bytes
     */
    private static byte[] hexToByte(String b) {

        if (b == null) {
            throw new NullPointerException();
        }

        final int len = b.length() / 2;
        byte[] retorno = new byte[len];
        try {
            for (int i = 0; i < len; i++) {
                retorno[i] = Integer.decode(
                        "0x" + b.substring((i * 2), (i * 2) + 2)).byteValue();
            }
        } catch (Throwable t) {
            throw new RuntimeException("Convert Hex to Byte: ", t);
        }
        return retorno;
    }

    /**
     * Criptografa uma String com o algoritimo DES
     *
     * @param key
     * @param data
     * @return String criptografada
     */
    public static String encrypt(final String key, final String data) {

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        try {
        	Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            return byteToHex(cipher.doFinal(data.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(CIPHER_ERROR, e);
        }
    }

    /**
     * Descriptografa uma String com o algoritimo DES
     *
     * @param key
     * @param data
     * @return String descriptografada
     */
    public static byte[] decrypt(final String key, final String data) {

        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

        try {
        	Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            return cipher.doFinal(hexToByte(data));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(CIPHER_ERROR_2, e);
        }

    }

    /**
     * Retorna o Texto substituindo <code>\n ou \r por <br/></code>
     *
     * @param texto
     * @return texto formatado
     */
    public static String trataEnter(final String texto) {
        if (texto == null) {
            return null;
        }

        return texto.replaceAll("\r?\n", "<br/>");
    }

    /**
     * Retorna o Texto substituindo <code>"<" por &lt; e ">" por &gt;</code>
     *
     * @param texto
     * @return texto formatado
     */
    public static String trataTagsHtml(final String texto) {
        if (texto == null) {
            return null;
        }

        return texto.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }


    /**
     * Trata plic na String para que o SQL não retorne erro
     *
     * @param texto
     *            Texto aser tratado
     *
     */
    public static String trataPlic(final String texto) {
        String textoRetorno = "";

        if (texto != null) {
            textoRetorno = texto.replaceAll("'", "''");
            textoRetorno = textoRetorno.replaceAll("/'", "''");
        }

        return textoRetorno;
    }

    /**
     * Trata aspas na String para não dar erro na exibição da msg
     *
     * @param texto
     *            Texto a ser tratado
     *
     */
    public static String trataAspas(final String texto) {
        String textoRetorno = "";

        if (texto != null) {
            textoRetorno = texto.replaceAll("\"", "'");
        }

        return textoRetorno;
    }

    /**
     * Método que retorna o diretório raiz da aplicação
     * @param daoFactory Fonte de conexão
     * @return String com diretorio raiz. <i>Ex: /opt/websites/NOME_SISTEMA/</i>
     * @throws DaoException
     */
    public static String obterDiretorioRaiz(DaoFactory daoFactory) throws DaoException{
    	String dirRaiz = null;

    	//Obtem os parâmetros gerais para poder pegar o caminho absoluto das imagens
		ParametrosGeraisCtrl ctrlParam = new ParametrosGeraisCtrl(
                daoFactory);

		List lstParam = ctrlParam.list();

        // Se for recuperada a lista de parâmetros, lê o primeiro registro (a princípio só existirá um)
        if (!lstParam.isEmpty()) {
            ParametrosGerais param = (ParametrosGerais) lstParam.get(0);
            dirRaiz = param.getDiretorioContextoAplicacao();
        } else {
        	dirRaiz = "";
        }

        return dirRaiz;
    }

    /**
     * Método que retorno o nome do contexto sem barras
     * @param daoFactory Fonte de conexão
     * @return nome do contexto da aplicação
     * @throws DaoException
     */
    public static String obterContextoAplicacao(DaoFactory daoFactory) throws DaoException{
    	String caminho = obterDiretorioRaiz(daoFactory);
    	String contexto = null;
    	if (caminho.lastIndexOf("/") == caminho.length()-1){
    		caminho = caminho.substring(0, caminho.length()-1);
		} else {
			caminho = new String(caminho);
		}

		int barraAnterior = caminho.lastIndexOf("/");
		contexto = caminho.substring(barraAnterior+1);

    	return contexto;
    }

    /**
     * Método que retorna o domínio de uma aplicação até a porta, sem barra no fim
     * @param request
     * @return dominio da aplicação. (Ex: http://localhost:8080)
     */
    public static String obterDominioAplicacao(HttpServletRequest request) {
		final String domain = request.getScheme()
				+ "://".concat(request.getServerName()).concat(":")
						.concat(String.valueOf(request.getServerPort()));
    	return domain;
    }

}