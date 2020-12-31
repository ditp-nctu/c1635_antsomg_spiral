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
package art.cctcc.c1635.antsomg.demo.x;

import art.cctcc.c1635.antsomg.demo.x.Vertex_X.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Graph_X extends StandardGraph<Edge_X, Vertex_X> {

    public Graph_X(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var stay = Vertex_X.get(X.STAY);
        var in = Vertex_X.get(X.IN);
        var out = Vertex_X.get(X.OUT);

        this.setStart(stay);
        var S_I = new Edge_X(stay, in, 10.0);
        var S_O = new Edge_X(stay, out, 1.0);
        var I_O = new Edge_X(in, out, 1.0);
        var O_O = new Edge_X(out, out, 500.0);
        var S_S = new Edge_X(stay, stay, 1.0);
        this.addEdges(
                S_I, S_I.getReverse(1.0),
                S_O, S_O.getReverse(10.0),
                I_O, O_O, S_S);
    }
}
