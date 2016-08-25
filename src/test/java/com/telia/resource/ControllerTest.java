/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.telia.resource;

import com.telia.Application;
import com.telia.model.Error;
import com.telia.model.UserResponse;
import com.telia.model.util.CustomMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.telia.resource.Controller.ERROR;
import static com.telia.resource.Controller.NOT_FOUND;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void getUser() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get("/e164/+4781549300"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        CustomMapper<UserResponse> mapper = new CustomMapper<>(UserResponse.class);
        UserResponse userResponse = mapper.deserialize(response.getContentAsString());

        assertThat(userResponse.getUser().getGivenName(), is(not(nullValue())));
        assertThat(userResponse.getUser().getFamilyName(), is(not(nullValue())));
        assertThat(userResponse.getUser().getPhoneNumber(), is(not(nullValue())));
        assertThat(userResponse.getUser().getExternalId(), is(equalTo("123")));
        assertThat(userResponse.getIssuerId(), is(equalTo("issuerId")));
    }

    @Test
    public void userNotFound() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get(String.format("/e164/%s", NOT_FOUND)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn().getResponse();

        CustomMapper<Error> mapper = new CustomMapper<>(Error.class);
        Error error = mapper.deserialize(response.getContentAsString());

        assertThat(error.getError(), containsString(NOT_FOUND));
    }

    @Test
    public void internalError() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(get(String.format("/e164/%s", ERROR)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn().getResponse();

        CustomMapper<Error> mapper = new CustomMapper<>(Error.class);
        Error error = mapper.deserialize(response.getContentAsString());

        assertThat(error.getError(), containsString("terrible"));
    }
}