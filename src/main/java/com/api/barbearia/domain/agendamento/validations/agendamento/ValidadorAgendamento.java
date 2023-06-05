package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.controller.agendamento.dto.AgendamentoDadosCadastro;

//USANDO O POLIMORFISMO
//Princípios do SOLID
//Single Responsibility Principle (Princípio da responsabilidade única)
//Open-Closed Principle (Princípio aberto-fechado)
//Dependency Inversion Principle (Princípio da inversão de dependência)
public interface ValidadorAgendamento {

    //METODOS COM O MESMO NOME E ASSINATURA, E O CORPO DO METODO EM CADA CLASSE QUE FAZ ALGO DIFERENTE.
    void validar(AgendamentoDadosCadastro dados);
}
