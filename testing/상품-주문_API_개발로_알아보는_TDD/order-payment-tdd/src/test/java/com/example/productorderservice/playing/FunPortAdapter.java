package com.example.productorderservice.playing;

public class FunPortAdapter implements FunPort {

    private FunRepository funRepository;

    public FunPortAdapter(FunRepository funRepository) {
        this.funRepository = funRepository;
    }

    @Override
    public void save(Fun fun) {
        funRepository.save(fun);
    }
}
