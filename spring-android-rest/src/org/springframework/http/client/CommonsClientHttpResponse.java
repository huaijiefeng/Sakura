/*
 * Copyright 2002-2014 the original author or authors.
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

package org.springframework.http.client;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;

/**
 * {@link org.springframework.http.client.ClientHttpResponse} implementation that uses
 * Apache Commons HttpClient to execute requests.
 *
 * <p>Created via the {@link CommonsClientHttpRequest}.
 *
 * @author Arjen Poutsma
 * @author Roy Clarkson
 * @since 1.0
 * @see CommonsClientHttpRequest#execute()
 * @deprecated In favor of {@link HttpComponentsClientHttpResponse}
 */
@Deprecated
final class CommonsClientHttpResponse extends AbstractClientHttpResponse {

	private final HttpMethod httpMethod;

	private HttpHeaders headers;


	CommonsClientHttpResponse(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}


	public int getRawStatusCode() {
		return this.httpMethod.getStatusCode();
	}

	public String getStatusText() {
		return this.httpMethod.getStatusText();
	}

	public HttpHeaders getHeaders() {
		if (this.headers == null) {
			this.headers = new HttpHeaders();
			for (Header header : this.httpMethod.getResponseHeaders()) {
				this.headers.add(header.getName(), header.getValue());
			}
		}
		return this.headers;
	}

	@Override
	protected InputStream getBodyInternal() throws IOException {
		return this.httpMethod.getResponseBodyAsStream();
	}

	@Override
	protected void closeInternal() {
		this.httpMethod.releaseConnection();
	}

}