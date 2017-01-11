/*
 * Copyright 2012-2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.money.core.handlers

import com.comcast.money.api.{ SpanHandler, SpanInfo, Tag, SpanId }
import com.comcast.money.core.{ CoreSpan, CoreSpanInfo }
import com.typesafe.config.Config

class ConfiguredHandler extends ConfigurableHandler {

  var calledConfigure = false

  def configure(config: Config): Unit = calledConfigure = true
  def handle(span: SpanInfo): Unit = ()
}

class NonConfiguredHandler extends SpanHandler {
  def handle(span: SpanInfo): Unit = ()
}

trait TestData {

  import scala.collection.JavaConversions._

  val testStringNote = Tag.of("str", "bar")
  val testLongNote = Tag.of("lng", 200L)
  val testDoubleNote = Tag.of("dbl", 1.2)
  val testBooleanNote = Tag.of("bool", true)

  val testSpanInfo = CoreSpanInfo(
    id = new SpanId(),
    startTimeMillis = System.currentTimeMillis,
    startTimeMicros = System.nanoTime() / 1000,
    endTimeMillis = System.currentTimeMillis,
    endTimeMicros = System.nanoTime / 1000,
    durationMicros = 123456L,
    name = "test-span",
    appName = "test",
    host = "localhost",
    tags = Map("str" -> testStringNote, "lng" -> testLongNote, "dbl" -> testDoubleNote, "bool" -> testBooleanNote)
  )

  val testSpan = CoreSpan(new SpanId(), "test-span", null)
}