/*
 * Copyright (C) 2011 Google Inc.
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
 */

package test.rosjava_bar_pkg.custom_service;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.service.ServiceResponseBuilder;
import org.ros.node.service.ServiceServer;

/**
 * This is a simple {@link ServiceServer} {@link NodeMain}.
 * 
 * @author damonkohler@google.com (Damon Kohler)
 */
public class Server extends AbstractNodeMain {

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava_tutorial_services/server");
  }

  @Override
  public void onStart(ConnectedNode connectedNode) {
    connectedNode.newServiceServer("add_three_ints", foo_custom_msg.AddThreeInts._TYPE,
        new ServiceResponseBuilder<foo_custom_msg.AddThreeIntsRequest, foo_custom_msg.AddThreeIntsResponse>() {
          @Override
          public void
              build(foo_custom_msg.AddThreeIntsRequest request, foo_custom_msg.AddThreeIntsResponse response) {
            response.setResult(request.getA() + request.getB() + request.getC());
          }
        });
  }
}

