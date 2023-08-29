package com.openapi.restdoc.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceSnippetParameters.builder;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.epages.restdocs.apispec.Schema;
import com.openapi.restdoc.BaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Gyuhwan
 */
class ItemControllerTest extends BaseIntegrationTest {

    @DisplayName("Get Item 테스트")
    @Test
    void getItem() throws Exception {
        // given
        mockMvc.perform(
                get("/items")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("쌍쌍바"))
            .andDo(document("item-get",
                builder()
                    .tag("테스트")
                    .summary("Get Item Test")
                    .description("아이템이 오는지 확인한다.")
                    .responseSchema(Schema.schema("MainResponse.Get"))
                ,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                    , fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격")
                    , fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("수량")
                )
            ));
    }
}