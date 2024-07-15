package org.example.trafficlawhelper.service.etl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.vectorstore.VectorStore;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ETLServiceTest {

    @Mock
    private VectorStore vectorStore;

    @InjectMocks
    private ETLService etlService;

    @Test
    @DisplayName("유효한 파일 경로가 주어지면 파일로부터 텍스트를 추출하고 저장해야 한다.")
    void givenValidPdfPath_whenLoad_thenTokenizeAndStoreToVectorDB() {
        // given
        String path = "sample.pdf";

        // when
        etlService.load(path);

        // then
        verify(vectorStore, times(1)).accept(anyList());
    }
}