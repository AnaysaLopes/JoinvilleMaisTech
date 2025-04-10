package br.futurodev.joinville.exercicios.services;


import br.futurodev.joinville.exercicios.models.Collector;
import br.futurodev.joinville.exercicios.models.Contract;
import br.futurodev.joinville.exercicios.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {

    @Autowired
    private RouteService routeService;

    @Autowired
    private CollectorService collectorService;

    public Contract create(Contract contract){

       Collector collector = collectorService.finById(contract.getCollector().getId());
       if (collector == null) {
           return null;
       } else {
           contract.setCollector(collector);
       }

       List<Route> routes = new ArrayList<>();
       for (Route route : contract.getRoutes()) {
           Route foundeRoute = routeService.finById(route.getId());
           if (foundeRoute == null) {
               return null;
           }else {
               routes.add(foundeRoute);
           }
       }
        contract.setRoutes(routes);

        return Contract.addContract(contract);
    }

    public List<Contract> findAll(){

        return Contract.getContracts();
    }
}
