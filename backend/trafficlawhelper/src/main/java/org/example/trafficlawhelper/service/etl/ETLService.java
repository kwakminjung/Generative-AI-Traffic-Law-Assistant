package org.example.trafficlawhelper.service.etl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ETLService {
    private static final Logger logger = LoggerFactory.getLogger(ETLService.class);

    private final VectorStore vectorStore;

    public void load(String path) {
        PagePdfDocumentReader pdfReader = createPdfReader(path);
        TokenTextSplitter splitter = new TokenTextSplitter();

        List<Document> documents = pdfReader.get();
        List<Document> tokenizedDocuments = splitter.apply(documents);
        vectorStore.accept(tokenizedDocuments);

        logger.info("Successfully loaded the document from {}", path);
    }

    private PagePdfDocumentReader createPdfReader(String path) {
        return new PagePdfDocumentReader(path,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfBottomTextLinesToDelete(3)
                                .build())
                        .withPagesPerDocument(1)
                        .build());
    }
}
