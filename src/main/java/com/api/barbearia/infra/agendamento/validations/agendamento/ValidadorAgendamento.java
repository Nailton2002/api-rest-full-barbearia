package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;

//USANDO O POLIMORFISMO
//Princípios do SOLID
//Single Responsibility Principle (Princípio da responsabilidade única)
//Open-Closed Principle (Princípio aberto-fechado)
//Dependency Inversion Principle (Princípio da inversão de dependência)
public interface ValidadorAgendamento {

    //METODOS COM O MESMO NOME E ASSINATURA, E O CORPO DO METODO EM CADA CLASSE QUE FAZ ALGO DIFERENTE.
    void validar(AgendamentoRequest request);
}
