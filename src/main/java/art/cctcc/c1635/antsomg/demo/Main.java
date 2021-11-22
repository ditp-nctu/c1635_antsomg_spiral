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
package art.cctcc.c1635.antsomg.demo;

import java.util.Map;
import java.util.stream.Collectors;
import java.time.Instant;
import java.awt.Color;
import static java.util.function.Predicate.not;
import processing.core.PApplet;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;
import static art.cctcc.c1635.antsomg.demo.x.Vertex_X.X.*;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Main extends PApplet {

  int size = 700;
  SpiralSystem demo;
  float theta;
  Map<SpiralAnt, Float> radius;

  @Override
  public void settings() {
    size(size, size);
  }

  @Override
  public void setup() {
    colorMode(RGB);
    background(0);
    noFill();
    try {
      demo = new SpiralSystem(200, Long.parseLong(args[0]));
    } catch (Exception ex) {
      demo = new SpiralSystem(200, Instant.now().getEpochSecond());
    }
    demo.init_graphs();
    demo.init_population();
    radius = demo.ants.stream()
            .collect(Collectors.toMap(ant -> ant, ant -> 10f));
  }
  float move_amount = 2.5f;
  float delta_theta = 1f;

  @Override
  public void draw() {

    demo.ants.stream()
            .filter(not(SpiralAnt::isCompleted))
            .forEach(ant -> {
              var move = ant.getCurrentTrace().getX().getSelected().getTo();
              var r = radius.get(ant);
              if (IN.equals(move.e()) && r > move_amount) {
                r -= move_amount;
              }
              if (OUT.equals(move.e())) {
                r += move_amount;
              }
              radius.replace(ant, r);
              float x = size / 2 + r * cos(this.theta),
                      y = size / 2 + r * sin(this.theta);
              switch (ant.currentTrace.getY().getSelected().getTo().e()) {
                case WHITE -> {
                  stroke(Color.WHITE.getRGB());
                }
                case RED -> {
                  stroke(Color.RED.getRGB());
                }
                case YELLOW -> {
                  stroke(Color.YELLOW.getRGB());
                }
                case BLUE -> {
                  stroke(Color.BLUE.getRGB());
                }
              }
              point(x, y);
            });

    this.theta += delta_theta * PI / 180;
    if (demo.isAimAchieved()) {
      demo.getGraphs().values().stream()
              .map(StandardGraph::asGraphviz)
              .forEach(System.out::println);
      noLoop();
    } else {
      demo.navigate();
    }
  }

  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale", "1.0");
    PApplet.main(Main.class, args);
  }
}
