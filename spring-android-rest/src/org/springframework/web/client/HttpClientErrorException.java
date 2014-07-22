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

package org.springframework.web.client;

import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;

/**
 * Exception thrown when an HTTP 4xx is received.
 *
 * @author Arjen Poutsma
 * @author Roy Clarkson
 * @since 1.0
 * @see DefaultResponseErrorHandler
 */
public class HttpClientErrorException extends HttpStatusCodeException {

	private static final long serialVersionUID = 6777393766937023392L;

	/**
	 * Construct a new instance of {@code HttpClientErrorException} based on a {@link org.springframework.http.HttpStatus}.
	 * @param statusCode the status code
	 */
	public HttpClientErrorException(HttpStatus statusCode) {
		super(statusCode);
	}

	/**
	 * Construct a new instance of {@code HttpClientErrorException} based on a {@link org.springframework.http.HttpStatus} and status text.
	 * @param statusCode the status code
	 * @param statusText the status text
	 */
	public HttpClientErrorException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	/**
	 * Construct a new instance of {@code HttpClientErrorException} based on a {@link org.springframework.http.HttpStatus}, status text, and
	 * response body content.
	 *
	 * @param statusCode	  the status code
	 * @param statusText	  the status text
	 * @param responseBody	the response body content, may be {@code null}
	 * @param responseCharset the response body charset, may be {@code null}
	 */
	public HttpClientErrorException(HttpStatus statusCode,
			String statusText,
			byte[] responseBody,
			Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}
}
