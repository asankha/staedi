/*******************************************************************************
 * Copyright 2017 xlate.io LLC, http://www.xlate.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package io.xlate.edi.stream.validation;

import io.xlate.edi.schema.EDISyntaxRule;
import io.xlate.edi.stream.Location;
import io.xlate.edi.stream.internal.EventHandler;

import java.util.List;

class ExclusionSyntaxValidator extends SyntaxValidator {

    private static final ExclusionSyntaxValidator singleton = new ExclusionSyntaxValidator();

    private ExclusionSyntaxValidator() {
    }

    static ExclusionSyntaxValidator getInstance() {
        return singleton;
    }

    @Override
    void validate(EDISyntaxRule syntax, Location location, List<UsageNode> children, EventHandler handler) {
        SyntaxStatus status = scanSyntax(syntax, children);

        if (status.elementCount > 1) {
            signalExclusionError(syntax, location, children, handler);
        }
    }
}
