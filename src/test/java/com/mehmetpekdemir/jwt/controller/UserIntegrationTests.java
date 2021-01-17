package com.mehmetpekdemir.jwt.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@DisplayName("UserIntegrationTests")
class UserIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_successful_login() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"Mehmet2\",\n" +
                        "    \"password\": \"Mehmet2\"\n" +
                        "}";

        mockMvc.perform(
                post("/api/login")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

    @Test
    void test_unsuccessful_login_throw_invalid_username_or_password_supplied() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"Mehmet2\",\n" +
                        "    \"password\": \"Wrong\"\n" +
                        "}";

        final String responseContent = "{\n" +
                "    \"statusCode\": 400,\n" +
                "    \"message\": \"Invalid username/password supplied\",\n" +
                "    \"date\": " + LocalDate.now().toString() + "\n" +
                "}";

        mockMvc.perform(
                post("/api/login")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isBadRequest()
        ).andExpect(
                content().json(responseContent)
        ).andDo(print());
    }

    @Test
    void test_unsuccessful_login_throw_user_not_found() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"Wrong\",\n" +
                        "    \"password\": \"Wrong\"\n" +
                        "}";

        final String responseContent = "{\n" +
                "    \"statusCode\": 400,\n" +
                "    \"message\": \"User Not Found !\",\n" +
                "    \"date\": " + LocalDate.now().toString() + "\n" +
                "}";

        mockMvc.perform(
                post("/api/login")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isBadRequest()
        ).andExpect(
                content().json(responseContent)
        ).andDo(print());
    }

    @Test
    void test_successful_sign_up() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"Test\",\n" +
                        "    \"password\": \"Test\",\n" +
                        "    \"role\":\"USER\"\n" +
                        "}";

        final String responseContent = "{\n" +
                "    \"message\": \"Successfully Created.\",\n" +
                "    \"date\": " + LocalDate.now().toString() + "\n" +
                "}";

        mockMvc.perform(
                post("/api/sign-up")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                content().json(responseContent)
        ).andDo(print());
    }

    @Test
    void test_unsuccessful_sign_up_throw_username_must_be_unique() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"Mehmet1\",\n" +
                        "    \"password\": \"Mehmet1\",\n" +
                        "    \"role\":\"USER\"\n" +
                        "}";

        final String responseContent = "{\n" +
                "    \"statusCode\": 400,\n" +
                "    \"message\": \"User name must be unique\",\n" +
                "    \"date\": " + LocalDate.now().toString() + "\n" +
                "}";

        mockMvc.perform(
                post("/api/sign-up")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                content().json(responseContent)
        ).andDo(print());
    }

    @Test
    void test_unsuccessful_sign_up_throw_role_not_found() throws Exception {
        final String bodyContent =
                "{\n" +
                        "    \"username\": \"qweqwe\",\n" +
                        "    \"password\": \"qweqwe\",\n" +
                        "    \"role\":\"USqER\"\n" +
                        "}";

        final String responseContent = "{\n" +
                "    \"statusCode\": 400,\n" +
                "    \"message\": \"Role Not Found !\",\n" +
                "    \"date\": " + LocalDate.now().toString() + "\n" +
                "}";

        mockMvc.perform(
                post("/api/sign-up")
                        .content(bodyContent)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isBadRequest()
        ).andExpect(
                content().json(responseContent)
        ).andDo(print());
    }

}