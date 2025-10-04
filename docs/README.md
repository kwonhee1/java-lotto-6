model 
    Lotto : 번호만 저장
        validateLottoNumbers
        validateLottoLength
        (LottoResulType) getResult(Lotto result, int bonus)

    LottoMachine : 여러 로또 생성 / 관리
        private Lotto[];
        create Lotto(int count);
        Lotto[] getAllLottoes()
        LottoResultDto getResult(Lotto result, int bonus)
    LottoResultType
    LottoResult
        private int totalCount
        private Map<LottoResulType, count> resultMap
        addResult(LottoResulType)
        toString()
    LottoMachineTest
        getResult가 올바르게 작동하는지
        input validate가 잘 절용 됬는지
        

view 
    input view 
        값을 Type으로 변환해주는 것 까지가 input view의 쓸모
    output view

controller
    validate user input
    getResult(List<Integer> resultNumbers)