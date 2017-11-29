/*
 * Copyright 2014
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.template.mustache;

import java.util.HashMap;
import java.util.Map;

import spark.servlet.SparkApplication;
import spark.ModelAndView;

import static spark.Spark.get;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Mustache template engine example
 *
 * @author Sam Pullara https://github.com/spullara
 */
public class MustacheTemplateExample implements SparkApplication {

  public static void main(String[] args) {
    new MustacheTemplateExample().init();
  }

  @Override
  public void init() {

    get("/hello", (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Hello Mustache!");
        model.put("title1", "Hello Mustache!");
        model.put("title2", "Hello Mustache!");
        model.put("title3", "Hello Mustache!");
        model.put("list_item1", "abcde fghij klmno");
        model.put("list_item2", "pqrst uvwxy zABCD");
        model.put("list_item3", "EFGHI JKLMN OPQRS TUVWX YZ");
        model.put("message1", "Hello Mustache!");
        model.put("message2", "Hello Mustache!");
        return new ModelAndView(model, "hello.mustache"); // hello.mustache file is in resources/templates directory
    }, new MustacheTemplateEngine());

  }
  // Use Servlet annotation to define the Spark filter without web.xml:
  @WebFilter(
          filterName = "SparkInitFilter", urlPatterns = {"/*"}, 
          initParams = {
             @WebInitParam(name = "applicationClass", value = "spark.template.mustache.MustacheTemplateExample")
      })
  public static class SparkInitFilter extends spark.servlet.SparkFilter {
  }

}
