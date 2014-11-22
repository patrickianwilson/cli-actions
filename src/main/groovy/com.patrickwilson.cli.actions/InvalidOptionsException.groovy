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

package com.patrickwilson.cli.actions

/**
 * Throw if the provided options are not valid for the specified action.
 * User: pwilson
 */
class InvalidOptionsException extends Exception {

    public InvalidOptionsException(String optionProblemMessage) {
        super(optionProblemMessage)
    }

}
