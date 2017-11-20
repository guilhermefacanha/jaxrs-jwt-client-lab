package lab.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChaveValor<CHAVE extends Object, VALOR extends Object> {
	CHAVE chave;
	VALOR valor;
}
