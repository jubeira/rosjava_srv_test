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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a simple {@link ServiceServer} {@link NodeMain}.
 * 
 * @author damonkohler@google.com (Damon Kohler)
 */
public class ProductServer extends AbstractNodeMain {

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava_tutorial_services/server");
  }

  @Override
  public void onStart(final ConnectedNode connectedNode) {
      connectedNode.newServiceServer("get_product", foo_custom_msg.GetProduct._TYPE,
              new ServiceResponseBuilder<foo_custom_msg.GetProductRequest, foo_custom_msg.GetProductResponse>() {
              @Override
              public void build(foo_custom_msg.GetProductRequest request, foo_custom_msg.GetProductResponse response) {
                  connectedNode.getLog().info("Received product name: " + request.getProductName());
                  response.setBoxCounter(new long[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
                  response.setBoxes(new ArrayList<>(Arrays.asList("hello", "foo", "bar")));
                  response.setSize(123);
              }
      });
  }
}

