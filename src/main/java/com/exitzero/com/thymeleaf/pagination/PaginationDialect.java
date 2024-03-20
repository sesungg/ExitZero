package com.exitzero.com.thymeleaf.pagination;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

public class PaginationDialect extends AbstractProcessorDialect {

	public static final String NAME = "Pagination";
	public static final String DEFAULT_PREFIX = "th";
	public static final int PROCESSOR_PRECEDENCE = 800;
	private String charset;

	public PaginationDialect(String charset) {
		super(NAME, DEFAULT_PREFIX, PROCESSOR_PRECEDENCE);
		this.charset = charset;
	}

    @Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new PaginationProcessor(TemplateMode.HTML,
				dialectPrefix, this.charset));
		return processors;
	}
}
