package itstep.learning.ioc;

import itstep.learning.services.generators.GeneratorService;
import itstep.learning.services.hash.HashService;

import javax.inject.Inject;
import javax.inject.Named;

public class IocDemo {
    private final HashService digestHashService;
    private final HashService signatureHashService;
    private final GeneratorService generatorService;

    @Inject
    public IocDemo(
            @Named("digest") HashService digestHashService,
            @Named("signature") HashService signatureHashService,
            @Named("generator")GeneratorService generatorService
    ) {
        this.digestHashService = digestHashService;
        this.signatureHashService = signatureHashService;
        this.generatorService = generatorService;
    }
    public void run(){
//        System.out.println("Ioc");
//        System.out.println(digestHashService.digest("123"));
//        System.out.println(signatureHashService.digest("123"));
        System.out.println("Suggested file name: " + generatorService.getNameFile());
        System.out.println("Generated crypto salt: " + digestHashService.digest(generatorService.getCryptoSalt()));
        System.out.println("Your OTP: " + generatorService.getOTP());
        System.out.println("Your password: " + generatorService.getPP());
    }
}
