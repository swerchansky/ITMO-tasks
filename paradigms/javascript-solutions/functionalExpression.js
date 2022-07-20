"use strict";

const cnst = value => () => Number(value);

const op = op => (...funct) => (...args) => op(...funct.map(f => f(...args)));

const getVariable = {
    "x": 0,
    "y": 1,
    "z": 2
};

const variable = element => (...args) => args[getVariable[element]];
const add = op((left, right) => left + right);
const subtract = op((left, right) => left - right);
const multiply = op((left, right) => left * right);
const divide = op((left, right) => left / right);
const negate = op(element => -element);
const pi = cnst(Math.PI);
const e = cnst(Math.E);

let mapOfOperators = (str, stack) => {
    switch (str) {
        case "negate":
            return [negate, 1];
        case "+" :
            return [add, 2];
        case "-" :
            return [subtract, 2];
        case "*" :
            return [multiply, 2];
        case "/" :
            return [divide, 2];
        default:
            stack.push(str);
            switch (str) {
                case "x":
                case "y":
                case "z":
                    return [variable, 1];
                default:
                    return [cnst, 1];
            }
    }
};

const parse = (exp) => {
    exp = exp.split(" ").filter(str => str !== "")
    const result = exp.reduce((stack, el) => {
        const op = mapOfOperators(el, stack);
        stack.push(op[0](...stack.splice(-op[1])));
        return stack;
    }, []);
    return result.pop();
}

for (let i = 0; i <= 10; i++) {
    console.log(parse("x x * 2 x * - 1 +")(i))
}
