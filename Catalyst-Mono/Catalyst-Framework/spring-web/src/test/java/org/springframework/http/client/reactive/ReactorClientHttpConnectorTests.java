/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.http.client.reactive;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import reactor.netty.http.client.HttpClient;

import org.springframework.http.client.ReactorResourceFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Sebastien Deleuze
 */
class ReactorClientHttpConnectorTests {

	@Test
	void restartWithDefaultConstructor() {
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector();
		assertThat(connector.isRunning()).isTrue();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
		connector.stop();
		assertThat(connector.isRunning()).isFalse();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
	}

	@Test
	void restartWithExternalResourceFactory() {
		ReactorResourceFactory resourceFactory = new ReactorResourceFactory();
		resourceFactory.afterPropertiesSet();
		Function<HttpClient, HttpClient> mapper = Function.identity();
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector(resourceFactory, mapper);
		assertThat(connector.isRunning()).isTrue();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
		connector.stop();
		assertThat(connector.isRunning()).isFalse();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
	}

	@Test
	void restartWithHttpClient() {
		HttpClient httpClient = HttpClient.create();
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
		assertThat(connector.isRunning()).isTrue();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
		connector.stop();
		assertThat(connector.isRunning()).isFalse();
		connector.start();
		assertThat(connector.isRunning()).isTrue();
	}

}
