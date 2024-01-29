package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;

@CompiledJson(discriminator = "geometria")
public interface Geometria {
  String getGeometria();
  String getEstrutura();
  double getArea();
}
