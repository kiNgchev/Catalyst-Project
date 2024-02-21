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

package org.springframework.web.method.support

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.testfixture.method.ResolvableMethod
import org.springframework.web.testfixture.servlet.MockHttpServletRequest
import org.springframework.web.testfixture.servlet.MockHttpServletResponse

/**
 * Kotlin unit tests for {@link InvocableHandlerMethod}.
 *
 * @author Sebastien Deleuze
 */
class InvocableHandlerMethodKotlinTests {

	private val request: NativeWebRequest = ServletWebRequest(MockHttpServletRequest(), MockHttpServletResponse())

	private val composite = HandlerMethodArgumentResolverComposite()

	@Test
	fun intDefaultValue() {
		composite.addResolver(StubArgumentResolver(Int::class.java, null))
		val value = getInvocable(Handler::class.java, Int::class.java).invokeForRequest(request, null)

		Assertions.assertThat(getStubResolver(0).resolvedParameters).hasSize(1)
		Assertions.assertThat(value).isEqualTo("20")
	}

	@Test
	fun booleanDefaultValue() {
		composite.addResolver(StubArgumentResolver(Boolean::class.java, null))
		val value = getInvocable(Handler::class.java, Boolean::class.java).invokeForRequest(request, null)

		Assertions.assertThat(getStubResolver(0).resolvedParameters).hasSize(1)
		Assertions.assertThat(value).isEqualTo("true")
	}

	@Test
	fun nullableIntDefaultValue() {
		composite.addResolver(StubArgumentResolver(Int::class.javaObjectType, null))
		val value = getInvocable(Handler::class.java, Int::class.javaObjectType).invokeForRequest(request, null)

		Assertions.assertThat(getStubResolver(0).resolvedParameters).hasSize(1)
		Assertions.assertThat(value).isEqualTo("20")
	}

	@Test
	fun nullableBooleanDefaultValue() {
		composite.addResolver(StubArgumentResolver(Boolean::class.javaObjectType, null))
		val value = getInvocable(Handler::class.java, Boolean::class.javaObjectType).invokeForRequest(request, null)

		Assertions.assertThat(getStubResolver(0).resolvedParameters).hasSize(1)
		Assertions.assertThat(value).isEqualTo("true")
	}

	@Test
	fun unitReturnValue() {
		val value = getInvocable(Handler::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isNull()
	}

	@Test
	fun nullReturnValue() {
		composite.addResolver(StubArgumentResolver(String::class.java, null))
		val value = getInvocable(Handler::class.java, String::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isNull()
	}

	@Test
	fun valueClass() {
		composite.addResolver(StubArgumentResolver(Long::class.java, 1L))
		val value = getInvocable(Handler::class.java, Long::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isEqualTo(1L)
	}

	@Test
	fun valueClassDefaultValue() {
		composite.addResolver(StubArgumentResolver(Double::class.java))
		val value = getInvocable(Handler::class.java, Double::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isEqualTo(3.1)
	}

	@Test
	fun propertyAccessor() {
		val value = getInvocable(PropertyAccessorHandler::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isEqualTo("foo")
	}

	@Test
	fun extension() {
		composite.addResolver(StubArgumentResolver(CustomException::class.java, CustomException("foo")))
		val value = getInvocable(ExtensionHandler::class.java, CustomException::class.java).invokeForRequest(request, null)
		Assertions.assertThat(value).isEqualTo("foo")
	}

	@Test
	fun extensionWithParameter() {
		composite.addResolver(StubArgumentResolver(CustomException::class.java, CustomException("foo")))
		composite.addResolver(StubArgumentResolver(Int::class.java, 20))
		val value = getInvocable(ExtensionHandler::class.java, CustomException::class.java, Int::class.java)
			.invokeForRequest(request, null)
		Assertions.assertThat(value).isEqualTo("foo-20")
	}

	private fun getInvocable(clazz: Class<*>, vararg argTypes: Class<*>): InvocableHandlerMethod {
		val method = ResolvableMethod.on(clazz).argTypes(*argTypes).resolveMethod()
		val handlerMethod = InvocableHandlerMethod(clazz.constructors.first().newInstance(), method)
		handlerMethod.setHandlerMethodArgumentResolvers(composite)
		return handlerMethod
	}

	private fun getStubResolver(index: Int): StubArgumentResolver {
		return composite.resolvers[index] as StubArgumentResolver
	}

	private class Handler {

		fun intDefaultValue(limit: Int = 20) =
			limit.toString()

		fun nullableIntDefaultValue(limit: Int? = 20) =
			limit.toString()

		fun booleanDefaultValue(status: Boolean = true) =
			status.toString()

		fun nullableBooleanDefaultValue(status: Boolean? = true) =
			status.toString()

		fun unit(): Unit {
		}

		@Suppress("UNUSED_PARAMETER")
		fun nullable(arg: String?): String? {
			return null
		}

		fun valueClass(limit: LongValueClass) =
			limit.value

		fun valueClass(limit: DoubleValueClass = DoubleValueClass(3.1)) =
			limit.value
	}

	private class PropertyAccessorHandler {

		val prop: String
			get() = "foo"
	}

	private class ExtensionHandler {

		fun CustomException.handle(): String {
			return "${this.message}"
		}

		fun CustomException.handleWithParameter(limit: Int): String {
			return "${this.message}-$limit"
		}
	}

	@JvmInline
	value class LongValueClass(val value: Long)

	@JvmInline
	value class DoubleValueClass(val value: Double)

	class CustomException(message: String) : Throwable(message)

}
