# Generative-AI-Traffic-Law-Assistant
생성형 AI 기반 도로교통 법률 도우미

### 개요
OpenAI의 ChatGPT를 이용하여 도로교통 법률 챗봇을 서비스합니다. **검색 증강 생성(RAG)** 모델을 이용하여 보다 정확한 대답을 생성하며, 사용자는 도로교통 법률에 관한 다양한 질문에 대해 신뢰할 수 있는 정보를 빠르고 정확하게 얻을 수 있습니다. 도로교통 법규, 벌금, 사고 처리 절차 등 다양한 법률 관련 문제를 해결하는 데 발생하는 시간과 비용을 줄이고자 합니다.

### Retrieval Augmented Generation(RAG)
RAG 모델은 사전에 저장된 데이터를 벡터 데이터베이스로부터 가져와 AI가 정확한 답변을 생성하는 과정을 수행합니다. 데이터를 벡터 데이터베이스에 저장하는 처리 ETL 이라는 과정이 필요합니다. ETL은 
1. 텍스트 같은 정형 데이터나 PDF 같은 비정형 데이터에서 텍스트를 추출하고(Extract)
2. 추출한 데이터를 청크라는 작은 조각으로 쪼개어(Transform)
3. 벡터 데이터베이스에 저장하고 불러옵니다.(Load)

![image](https://github.com/user-attachments/assets/e65727ed-471c-4b1e-88e5-50c2074235da)

### Extract, Transform and Load 파이프라인
Spring AI에서 제공하는 인터페이스를 이용하여 ETL 파이프라인을 구축하였습니다.
![image](https://github.com/user-attachments/assets/d2cdc226-0f2d-4830-8b8c-39488b282973)
- `PagePdfDocumentReader` : Pdf 파일을 페이지 단위로 읽습니다.
- `TokenTextSplitter` : 파일에서 읽어들인 텍스트를 split 합니다.
- `VectorStore` : 청크(chunk) 단위로 쪼개진 텍스트를 Redis 벡터 DB에 저장합니다.
