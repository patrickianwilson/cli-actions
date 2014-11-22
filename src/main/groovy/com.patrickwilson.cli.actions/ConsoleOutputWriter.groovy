/***********************************************************************************************************************
 Licensed to Patrick Wilson Consulting under one
        or more contributor license agreements.  See the NOTICE file
        distributed with this work for additional information
        regarding copyright ownership.  Patrick Wilson Consulting licenses this file
        to you under the Apache License, Version 2.0 (the
        "License"); you may not use this file except in compliance
        with the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing,
        software distributed under the License is distributed on an
        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
        KIND, either express or implied.  See the License for the
        specific language governing permissions and limitations
        under the License.
 **********************************************************************************************************************/
package com.patrickwilson.cli.actions;

/**
 * TODO:  Give a descriptive Doc comment.
 * User: pwilson
 */
class ConsoleOutputWriter implements OutputWriter {

    @Override
    void printMessage(String message) {
        println(message)
    }

    @Override
    void printError(String error) {
        System.err.println(error)
    }
}
