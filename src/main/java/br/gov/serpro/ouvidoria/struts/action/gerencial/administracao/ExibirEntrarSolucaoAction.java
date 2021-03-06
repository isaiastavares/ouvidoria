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
package br.gov.serpro.ouvidoria.struts.action.gerencial.administracao;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.gov.serpro.ouvidoria.controller.andamento.SolucaoCtrl;
import br.gov.serpro.ouvidoria.dao.DaoException;
import br.gov.serpro.ouvidoria.model.Orgao;
import br.gov.serpro.ouvidoria.model.Solucao;
import br.gov.serpro.ouvidoria.struts.ActionSupport;

/**
 * Objetivo: Exibir detalhes da Solução consultada ou permitir entrada de nova
 * solução
 * 
 * @author SERPRO
 * @version $Revision: 1.1.2.5 $, $Date: 2011/10/21 12:51:36 $
 * @version 0.1, 2004/12/27
 *  
 */
public class ExibirEntrarSolucaoAction extends ActionSupport {

    /**
     * Método de execução da ação. Realiza dois forwards: um em caso de sucesso
     * e outro em caso de falha
     * 
     * @param mapping
     * @param form
     *            ActionForm, caso necessário
     * @param request
     * @param response
     *  
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages errorMsgs = new ActionMessages();

        try {
            SolucaoCtrl ctrlSolucao = new SolucaoCtrl(getDaoFactory());

            String id = request.getParameter("id");

            // Se foi passado um id de solução, trata-se de
            // uma alteração/exclusão
            if (id != null) {
                Solucao solucao = ctrlSolucao.get(new Long(id));

                // Se não encontrou a solucao, reporta o erro
                if (solucao == null) {
                    errorMsgs.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("error.solucao.notFound"));
                    saveMessages(request, errorMsgs);
                }

                // Passa através da requisição a solução escolhida
                // e a ação chamadora, caso exista
                request.setAttribute("Solucao", solucao);
            }

            //     Recupera a quantidade de utilização do script
            List qdtUtilizacao = ctrlSolucao.listaQtdUtilizacaoSolucao(id);                
            request.setAttribute("qdtUtilizacao", qdtUtilizacao.get(0));
            
            
            // Recupera órgão do funcionário
            Orgao org = getOrgao(request);

            // Recupera lista de assuntos do órgão para preenchimento da "combo"
            Collection lstAssuntos = org.getListaTodosAssuntos("Ativo");

            if (lstAssuntos.isEmpty()) {
                errorMsgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "error.orgao.semassunto"));

                saveMessages(request, errorMsgs);
            }

            // Passa a lista de assuntos do funcionário através da requisição
            // e a ação chamadora, caso exista
            request.setAttribute("lstAssuntos", lstAssuntos);

        } catch (DaoException e) {
            errorMsgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "error.solucao.excecao"));
            saveMessages(request, errorMsgs);
        }

        if (errorMsgs.isEmpty()) {
            return (mapping.findForward("success"));
        }
        return (mapping.findForward("failure"));

    }

}