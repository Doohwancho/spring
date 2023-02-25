package com.example.productorderservice.playing;

class FunService {
    private FunPort funPort;

    FunService(FunPort funPort) {
        this.funPort = funPort;
    }

    void addProduct(AddFunRequest request) {
        Fun fun = new Fun(request.name(), request.price(), request.funPolicy());
        funPort.save(fun);
    }
}
