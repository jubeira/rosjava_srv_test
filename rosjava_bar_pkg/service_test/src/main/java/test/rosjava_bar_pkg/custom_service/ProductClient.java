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

import org.ros.exception.RemoteException;
import org.ros.exception.RosRuntimeException;
import org.ros.exception.ServiceNotFoundException;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.service.ServiceClient;
import org.ros.node.service.ServiceResponseListener;

/**
 * A simple {@link ServiceClient} {@link NodeMain}.
 * 
 * @author damonkohler@google.com (Damon Kohler)
 */
public class ProductClient extends AbstractNodeMain {

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of("rosjava_tutorial_services/client");
  }

  @Override
  public void onStart(final ConnectedNode connectedNode) {
    ServiceClient<foo_custom_msg.GetProductRequest, foo_custom_msg.GetProductResponse> serviceClient;
    try {
      serviceClient = connectedNode.newServiceClient("get_product", foo_custom_msg.GetProduct._TYPE);
    } catch (ServiceNotFoundException e) {
      throw new RosRuntimeException(e);
    }
    final foo_custom_msg.GetProductRequest request = serviceClient.newMessage();
    request.setProductName("Foo product name");
    serviceClient.call(request, new ServiceResponseListener<foo_custom_msg.GetProductResponse>() {
      @Override
      public void onSuccess(foo_custom_msg.GetProductResponse response) {
        for (long l : response.getBoxCounter()) {
          connectedNode.getLog().info("Got number " + l);
        }

        for (String s : response.getBoxes()) {
          connectedNode.getLog().info("Got box " + s);
        }
        connectedNode.getLog().info("Product size: " + response.getSize());
      }

      @Override
      public void onFailure(RemoteException e) {
        throw new RosRuntimeException(e);
      }
    });
  }
}

