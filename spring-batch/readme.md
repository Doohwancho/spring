---\
Goal


modularize spring batch



---\
Concepts


a. MVP
b. config
c. job
d. steps
e. tasklet
f. chunk

x. bulletproof jobs
x. transactional management
x. test batch
x. monitoring jobs
x. scaling and parallel processing
x. controlling execution
x. entermprise integration



---\
Todo


a-1. simple spring batch MVP :white_check_mark:\
a-2. read csv -> apply date & id -> print :white_check_mark:

b-1. spring-batch build.gradle :white_check_mark:

c-1. @Scheduled(cron = "") :white_check_mark:
c-2. @JobScope :white_check_mark:\
c-3. @StepScope :white_check_mark:

d-1. single step :white_check_mark:\
d-2. multiple steps :white_check_mark:\
d-3. flow :white_check_mark:\
d-4. .startLimit() :white_check_mark:\
d-5. .skipLimit() :white_check_mark:\
d-6. .retryLimit() :white_check_mark:\
d-7. .noRollback() :white_check_mark:

e-1. lambda :white_check_mark:\
e-2. method invoke adapter :white_check_mark:\
e-3. external class :white_check_mark:

f-1. jdbc chunk :white_check_mark:\
f-2. mybatis chunk :white_check_mark:\
f-3. jpa chunk :white_check_mark:

---\
Reference


a-1. [spring batch mvp](https://github.com/warpgate3/spring-batch-tistory)

b,c,d,e,f. [spring batch 이해하고 사용하기](https://khj93.tistory.com/entry/Spring-Batch%EB%9E%80-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)
