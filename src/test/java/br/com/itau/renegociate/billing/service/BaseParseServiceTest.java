package br.com.itau.renegociate.billing.service;

import br.com.itau.renegociate.billing.config.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class BaseParseServiceTest<T> extends BaseTest {

    @Autowired
    protected ObjectMapper objectMapper;

    String getResponseMock() throws IOException {
        return FileUtils.readFileToString(getInputFileMock(),
                String.valueOf(StandardCharsets.UTF_8));
    }

    MultipartFile getInputMultipartFileMock() throws IOException {
        return new MockMultipartFile("response.json", new FileInputStream(getInputFileMock()));
    }

    MultipartFile getInputMultipartFileMock(String content) throws IOException {
        return new MockMultipartFile("response.json", new ByteArrayInputStream(content.getBytes()));
    }

    List<T> getResponseObjectsMock() throws IOException {
        String json = getResponseMock();
        return objectMapper.readValue(json, getResponseType());
    }

    List<T> getResponseObjectsMock(String response) throws IOException {
        return objectMapper.readValue(response, getResponseType());
    }

    ResponseEntity<List<T>> getResponseEntitySuccessMock() throws IOException {
        return ResponseEntity.ok(getResponseObjectsMock());
    }

    ResponseEntity<List<T>> getResponseEntitySuccessMock(String response) throws IOException {
        return ResponseEntity.ok(getResponseObjectsMock(response));
    }

    abstract TypeReference<List<T>> getResponseType();

    private File getInputFileMock() {
        return new File("src/test/resources/response.json");
    }
}
