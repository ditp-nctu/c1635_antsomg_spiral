/*
 * Copyright 2019 Jonathan Chang, Chun-yien <ccy@musicapoetica.org>.
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
package art.cctcc.c1635.antsomg.demo.y;

import static art.cctcc.c1635.antsomg.demo.y.Vertex_Y.Y.*;
import art.cctcc.c1635.antsomg.impl.StandardGraph;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Graph_Y extends StandardGraph<Edge_Y, Vertex_Y> {

    public Graph_Y(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var white = Vertex_Y.get(WHITE);
        var blue = Vertex_Y.get(BLUE);
        var red = Vertex_Y.get(RED);
        var yellow = Vertex_Y.get(YELLOW);

        this.setStart(white);

        var w_w = new Edge_Y(white, white, 1.0);
        var b_b = new Edge_Y(blue, blue, 1.0);
        var r_r = new Edge_Y(red, red, 1.0);
        var y_y = new Edge_Y(yellow, yellow, 1.0);
        var w_b = new Edge_Y(white, blue, 10.0);
        var b_w = new Edge_Y(blue, white, 10.0);
        var w_r = new Edge_Y(white, red, 10.0);
        var r_w = new Edge_Y(red, white, 10.0);
        var w_y = new Edge_Y(white, yellow, 10.0);
        var y_w = new Edge_Y(yellow, white, 10.0);
        this.addEdges(
                w_w, b_b, r_r, y_y,
                w_b, b_w,
                w_r, r_w,
                w_y, y_w);
    }

}
