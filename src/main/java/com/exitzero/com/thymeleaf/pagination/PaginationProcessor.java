package com.exitzero.com.thymeleaf.pagination;

import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import java.util.HashMap;

@Slf4j
public class PaginationProcessor extends AbstractAttributeTagProcessor {
    private static final int ATTR_PRECEDENCE = 1300;
    private static final String ATTR_NAME = "pagination";
    private final static String jsFunction = "fnLinkPage";

    private String charset;
    public PaginationProcessor(TemplateMode templateMode, String dialectPrefix, String charset) {
        super(templateMode, dialectPrefix,  null, false, ATTR_NAME, true, ATTR_PRECEDENCE, true);
        this.charset = charset;
    }

    @Override
    protected void doProcess(ITemplateContext context,
                             IProcessableElementTag tag, AttributeName attributeName,
                             String attributeValue, IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        PaginationInfo paginationInfo = (PaginationInfo) expression.execute(context);

        int firstPageNo = paginationInfo.getFirstPageNo();
        int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
        int totalPageCount = paginationInfo.getTotalPageCount();
        int pageSize = paginationInfo.getPageSize();
        int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
        int currentPageNo = paginationInfo.getCurrentPageNo();
        int lastPageNo = paginationInfo.getLastPageNo();


        final IModelFactory modelFactory = context.getModelFactory();

        final IModel model = modelFactory.createModel();
        model.add(modelFactory.createOpenElementTag("nav", "aria-label", "Page navigation"));
        model.add(modelFactory.createOpenElementTag("ul", "class", "pagination"));

        if (totalPageCount > pageSize) {
            if (firstPageNoOnPageList > pageSize) {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(firstPageNo), "prev_end", "&laquo;", "처음으로"), true);
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(firstPageNoOnPageList - 1), "prev", "&lsaquo;", "이전으로"), true);
            } else {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(firstPageNo), "prev_end", "&laquo;", "처음으로"), true);
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(firstPageNo), "prev", "&lsaquo;", "이전으로"), true);
            }
        }

        for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
            if (i == currentPageNo) {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(i), "active", String.valueOf(i), String.valueOf(i)), true);
            } else {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(i), "", String.valueOf(i), String.valueOf(i)), true);
            }
        }

        if (totalPageCount > pageSize) {
            if (lastPageNoOnPageList < totalPageCount) {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(firstPageNoOnPageList + pageSize), "next", "&rsaquo;", "다음으로"), true);
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(lastPageNo), "next_end", "&raquo;", "맨끝으로"), true);
            } else {
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(lastPageNo), "next", "&rsaquo;", "다음으로"), true);
                structureHandler.insertImmediatelyAfter(pageLabel(structureHandler, modelFactory, model, String.valueOf(lastPageNo), "next_end", "&raquo;", "맨끝으로"), true);
            }
        }

        model.add(modelFactory.createCloseElementTag("ul"));
        model.add(modelFactory.createCloseElementTag("nav"));

        structureHandler.replaceWith(model, false);
    }

    private IModel pageLabel(IElementTagStructureHandler structureHandler, IModelFactory modelFactory, IModel model, String onclickValue, String className, String symbol, String title){
        HashMap<String, String> attr = new HashMap<>();
        if (!className.isEmpty()){
            attr.put("class", "page-item " + className);
            attr.put("title", title);
        } else {
            attr.put("class", "page-item");
        }
        attr.put("onclick", jsFunction + "(" + onclickValue + "); return false;");
        model.add(modelFactory.createOpenElementTag("li", attr, AttributeValueQuotes.DOUBLE, false));
        model.add(modelFactory.createOpenElementTag("a", "class", "page-link"));
        model.add(modelFactory.createText(symbol));
        model.add(modelFactory.createCloseElementTag("a"));
        model.add(modelFactory.createCloseElementTag("li"));

        return model;
    }


}
