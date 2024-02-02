package github.gmess.models;

import java.util.HashSet;
import java.util.Set;
import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.geometrias.Geometria;
import github.gmess.utils.StrUtils;

@CompiledJson
public record Orcamento(
  String movel,
  Material material,
  @JsonAttribute(name = "preco_total")
  String precoTotal,
  Set<EstruturaValor> estruturas
) {

  public static Orcamento from(final Movel movel) {
    double precoFinal = 0;
    final var geometrias = movel.geometrias();
    final var material = movel.material();
    final Set<EstruturaValor> estruturas = new HashSet<>();

    for(Geometria geometria : movel.geometrias()) {
      final double precoGeometria = geometria.getArea() * material.getPrecoBase();
      precoFinal += precoGeometria;

      estruturas.add(EstruturaValor.from(geometria, StrUtils.doubleToReal(precoGeometria)));
    }

    return new Orcamento(
      movel.movel(),
      movel.material(),
      StrUtils.doubleToReal(precoFinal),
      estruturas
    );
  }
}
