
//currying
function multiply(a, b) {
  return a * b;
}

function multiplyX(x) {
  return function (a) {
    return multiply(a, x);
  }
}

function add(a, b) {
  return a + b;
}

function addX(x) {
  return function(a) {
    return add(x, a);
  }
}



const multiplyTwo = multiplyX(2);
const multiplyThree = multiplyX(3);

// (x * a) * b + c
// 2x * 3 + 4 ??

const addFour = addX(4);

/*
//case1) if not compose..?
const formula = function(x){
	return addX(4)(multiplyX(3)(addX(5)(multiplyX(2)(x))));
}
console.log(formula(10)); //79
*/


//case2) compose
function compose(...args) {
  return args.reduce(function(prevFunc, nextFunc) {
    return function(...values) {
      return nextFunc(prevFunc(...values));
    }
  }, k => k);
}

/// (((x * 2) + 5) * 3) + 4

const formula = compose(
  multiplyX(2),
  addX(5),
  multiplyX(3),
  addX(4),
);
  
  
console.log(formula(10)); //79

//10*2 = 20
//20+5 = 25
//25*3 = 75
//75+4 = 79




/*

---
what is compose?


커링으로 나눈 함수 순서대로 .method() 로 이어서 실행시키는 것.



---
why not use case1?


메서드 실행순서가 함수에 가장 안쪽부터 바깥쪽으로 나옴. <- 이렇게 진행됨. 
근데 사람은 -> 이쪽으로 읽어서 비직관적.

compose 패턴 사용하면, 함수 사용을 순서대로 적용 가능. 



*/
