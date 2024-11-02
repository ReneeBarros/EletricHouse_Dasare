package com.example.nativeeletrichouse.domain

class CalcularPlacaSolar() {

    // Media de consumo mensal

    // precisa saber quanto gasta energia no dia quantos watts gasta no dia


    // irradiação solar media do local onde será instalado (potencia do Sol (chamado))

            // acessa o site da cresesb para saber a irradiaçao solar
                    // pegar dados do potencial energetico no plano inclinado
            // preciso saber se tem um valor medio para simplificar o calculo pos não tem API
            // para conectar com a cresesb.
    // lembrando que a placa deve estar voltado para o norte

    // eficiencia da placa solar

    // ******* EXEMPLO DE CALCULO ********

    /*
    * CONSUMO ANUAL 3.631KW/ANO
    *
    * CONSUMO  MENSAL
    * consumo mensal =  consumo anual / 12 meses
    *
    * consumo mensal = 3.631 / 12 = 302,58kw/mes
    *
    * Cm = 302,58 KW/MES
    * CONSUMO DIARIO = CONSUMO MENSAL / 30 dias
    *
    * Consumo diario = 302,58 / 30 = 10,08kw/dias
    *
    * AGORA PEGAR O VALOR GASTO DIARIO DIVIDIR PELA MEDIA DE IRRADIAÇÃO SOLAR DIARIA:
    *
    * PARA SABER A POTENCIA de pico na irradiação solar NECESSARIA PARA CALCULAR AS QUANTIDADE DE PLACAS:
    *  exemplo de media de irradiação para esse exemplo = 4,43
    * ENTAO PEGAR 10,08 / 4,43 = 2,27KW/pico ( 2.270w)
    *
    * agora definir qual a potencia da placa utilizar para esse exemplo utilizar uma de 330w
    *
    *  2,27kw/pico (2270w) / pela potencia da placa escolhida de 330w
    *
    * quantidade de placa = 2270/330 = 6,87 para deizar numero fechado redondo será utilizado 7 placas
    *
    *
    *
    *
    *
    * */





}