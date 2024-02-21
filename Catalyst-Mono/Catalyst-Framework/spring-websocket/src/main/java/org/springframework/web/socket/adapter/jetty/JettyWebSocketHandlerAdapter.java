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

package org.springframework.web.socket.adapter.jetty;

import java.nio.ByteBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.websocket.api.Callback;
import org.eclipse.jetty.websocket.api.Frame;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketFrame;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketOpen;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.core.OpCode;

import org.springframework.util.Assert;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;

/**
 * Adapts {@link WebSocketHandler} to the Jetty WebSocket API.
 *
 * @author Rossen Stoyanchev
 * @since 4.0
 */
@WebSocket
public class JettyWebSocketHandlerAdapter {

	private static final ByteBuffer EMPTY_PAYLOAD = ByteBuffer.wrap(new byte[0]);

	private static final Log logger = LogFactory.getLog(JettyWebSocketHandlerAdapter.class);


	private final WebSocketHandler webSocketHandler;

	private final JettyWebSocketSession wsSession;


	public JettyWebSocketHandlerAdapter(WebSocketHandler webSocketHandler, JettyWebSocketSession wsSession) {
		Assert.notNull(webSocketHandler, "WebSocketHandler must not be null");
		Assert.notNull(wsSession, "WebSocketSession must not be null");
		this.webSocketHandler = webSocketHandler;
		this.wsSession = wsSession;
	}


	@OnWebSocketOpen
	public void onWebSocketOpen(Session session) {
		try {
			this.wsSession.initializeNativeSession(session);
			this.webSocketHandler.afterConnectionEstablished(this.wsSession);
		}
		catch (Exception ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}

	@OnWebSocketMessage
	public void onWebSocketText(String payload) {
		TextMessage message = new TextMessage(payload);
		try {
			this.webSocketHandler.handleMessage(this.wsSession, message);
		}
		catch (Exception ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}

	@OnWebSocketMessage
	public void onWebSocketBinary(ByteBuffer payload, Callback callback) {
		BinaryMessage message = new BinaryMessage(copyByteBuffer(payload), true);
		try {
			this.webSocketHandler.handleMessage(this.wsSession, message);
			callback.succeed();
		}
		catch (Exception ex) {
			callback.fail(ex);
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}

	@OnWebSocketFrame
	public void onWebSocketFrame(Frame frame, Callback callback) {
		if (OpCode.PONG == frame.getOpCode()) {
			ByteBuffer payload = frame.getPayload() != null ? frame.getPayload() : EMPTY_PAYLOAD;
			PongMessage message = new PongMessage(copyByteBuffer(payload));
			try {
				this.webSocketHandler.handleMessage(this.wsSession, message);
				callback.succeed();
			}
			catch (Exception ex) {
				callback.fail(ex);
				ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
			}
		}
		else {
			callback.succeed();
		}
	}

	private static ByteBuffer copyByteBuffer(ByteBuffer src) {
		ByteBuffer dest = ByteBuffer.allocate(src.remaining());
		dest.put(src);
		dest.flip();
		return dest;
	}

	@OnWebSocketClose
	public void onWebSocketClose(int statusCode, String reason) {
		CloseStatus closeStatus = new CloseStatus(statusCode, reason);
		try {
			this.webSocketHandler.afterConnectionClosed(this.wsSession, closeStatus);
		}
		catch (Exception ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Unhandled exception after connection closed for " + this, ex);
			}
		}
	}

	@OnWebSocketError
	public void onWebSocketError(Throwable cause) {
		try {
			this.webSocketHandler.handleTransportError(this.wsSession, cause);
		}
		catch (Exception ex) {
			ExceptionWebSocketHandlerDecorator.tryCloseWithError(this.wsSession, ex, logger);
		}
	}

}
