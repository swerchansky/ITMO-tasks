"use strict";

function Create(fun, toString, evaluate, diff, simplify, prefix = toString) {
    fun.prototype.toString = toString;
    fun.prototype.evaluate = evaluate;
    fun.prototype.diff = diff;
    fun.prototype.simplify = simplify;
    fun.prototype.prefix = prefix;
}

function Const(number) {
    this.num = number;
}

Create(Const,
    function () {
        return "" + this.num
    },
    function () {
        return Number(this.num)
    },
    function () {
        return new Const(0)
    }, function () {
        return this
    }
)


const getVariable = {
    "x": 0,
    "y": 1,
    "z": 2
};

function Variable(variable) {
    this.x = variable;
}

Create(Variable,
    function () {
        return this.x;
    },
    function (...args) {
        return args[getVariable[this.x]];
    },
    function (variable) {
        return variable === this.x ? new Const(1) : new Const(0);
    },
    function () {
        return this;
    }
)

function BinaryOperation(...argument) {
    this.args = argument;
}

Create(BinaryOperation,
    function () {
        return this.args.map(el => el.toString()).join(" ") + " " + this.type;
    },
    function (...vars) {
        return this.op(...this.args.map(el => el.evaluate(...vars)));
    },
    function (diffVar) {
        return this.derivative(diffVar, ...this.args);
    },
    function () {
        let a;
        if (this.args.every(el => el instanceof Const)) {
            a = new Const(this.evaluate());
        } else {
            let check = Object.create(this);
            let tmp = this.toString();
            // check.args = this.args.map((el) => el.simplify());
            for (let i = 0; i < this.args.length; i++) {
                check.args[i] = this.args[i].simplify();
            }
            if (check.toString() === tmp) {
                a = check;
            } else {
                a = check.simplify();
            }
        }
        return this.simp(a);
    },
    function () {
        return "(" + this.type + " " + this.args.map(el => el.prefix()).join(" ") + ")";
    }
)

function CreateOperation(fun, type, op, derivative, simp) {
    fun.prototype = Object.create(BinaryOperation.prototype);
    fun.prototype.type = type;
    fun.prototype.op = op;
    fun.prototype.derivative = derivative;
    fun.prototype.simp = simp;
}

function Add(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Add,
    "+",
    function (a, b) {
        return a + b;
    },
    function (diffVar, a, b) {
        return new Add(a.diff(diffVar), b.diff(diffVar));
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        for (let i = 0; i < check.args.length; i++) {
            if (check.args[i].toString() === "0") {
                return check.args[i === 1 ? 0 : 1];
            }
        }
        return check;
    }
)

function Subtract(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Subtract,
    "-",
    function (a, b) {
        return a - b;
    },
    function (diffVar, a, b) {
        return new Subtract(a.diff(diffVar), b.diff(diffVar));
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        for (let i = 0; i < check.args.length; i++) {
            if (check.args[i].toString() === "0") {
                return (i === 1 ? check.args[0] : new Negate(check.args[1]));
            }
        }
        return check;
    }
)

function Multiply(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Multiply,
    "*",
    function (a, b) {
        return a * b;
    },
    function (diffVar, a, b) {
        return new Add(
            new Multiply(a.diff(diffVar), b),
            new Multiply(a, b.diff(diffVar))
        );
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        for (let i = 0; i < check.args.length; i++) {
            if (check.args[i].toString() === "0") {
                return new Const(0);
            } else if (check.args[i].toString() === "1") {
                return check.args[i === 1 ? 0 : 1];
            }
        }
        return check;
    }
)


function Divide(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Divide,
    "/",
    function (a, b) {
        return a / b;
    },
    function (diffVar, a, b) {
        return new Divide(
            new Subtract(
                new Multiply(a.diff(diffVar), b),
                new Multiply(a, b.diff(diffVar))
            ),
            new Multiply(b, b)
        );
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        if (check.args[1] instanceof Multiply) {
            for (let i = 0; i < 2; i++) {
                if (check.args[1].args[i].toString() === check.args[0].toString()) {
                    return new Divide(new Const(1), check.args[1].args[i === 1 ? 0 : 1])
                }
            }
        }
        for (let i = 0; i < check.args.length; i++) {
            if (check.args[0].toString() === "0") {
                return new Const(0);
            } else if (check.args[1].toString() === "1") {
                return check.args[0];
            }
        }
        return check;
    }
)

function Negate(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Negate,
    "negate",
    function (a) {
        return -a;
    },
    function (diffVar, a) {
        return new Negate(a.diff(diffVar));
    },
    function (check) {
        if (check instanceof Const) {
            return new Const(check.evaluate());
        }
        return check;
    }
)

function Min3(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Min3,
    "min3",
    function (a, b, c) {
        return Math.min(a, b, c);
    }, function (diffVar, ...args) {
        return new Min3(...args.map(el => el.diff(diffVar)));
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        return check;
    }
)

function Sinh(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Sinh,
    "sinh",
    function (a) {
        return Math.sinh(a);
    },
    function (diffVar, a) {
        return new Multiply(new Cosh(a), a.diff(diffVar));
    },
)

function Cosh(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Cosh,
    "cosh",
    function (a) {
        return Math.cosh(a);
    },
    function (diffVar, a) {
        return new Multiply(new Sinh(a), a.diff(diffVar));
    },
)


function Max5(...args) {
    BinaryOperation.call(this, ...args);
}

CreateOperation(Max5,
    "max5",
    function (a, b, c, d, e) {
        return Math.max(a, b, c, d, e);
    },
    function (diffVar, ...args) {
        return new Max5(...args.map(el => el.diff(diffVar)));
    },
    function (check) {
        if (check instanceof Const || check instanceof Variable) {
            return check;
        }
        return check;
    }
)


let mapOfOperators = (str, stack) => {
    switch (str) {
        case "negate":
            return [Negate, 1];
        case "+" :
            return [Add, 2];
        case "-" :
            return [Subtract, 2];
        case "*" :
            return [Multiply, 2];
        case "/" :
            return [Divide, 2];
        case "min3":
            return [Min3, 3];
        case "max5":
            return [Max5, 5];
        default:
            stack.push(str);
            switch (str) {
                case "x":
                case "y":
                case "z":
                    return [Variable, 1];
                default:
                    return [Const, 1];
            }
    }
};

const parse = (exp) => {
    exp = exp.split(" ").filter(str => str !== "");
    const result = exp.reduce((stack, el) => {
        const op = mapOfOperators(el, stack);
        stack.push(new op[0](...stack.splice(-op[1])));
        return stack;
    }, []);
    return result.pop();
}


let operations = {
    "negate": [Negate, 1],
    "+": [Add, 2],
    "-": [Subtract, 2],
    "*": [Multiply, 2],
    "/": [Divide, 2],
    "sinh": [Sinh, 1],
    "cosh": [Cosh, 1]
}

function ParsingError(error) {
    this.message = error;
}

ParsingError.prototype = Object.create(Error.prototype);
ParsingError.prototype.name = "ParsingError";
ParsingError.prototype.constructor = ParsingError;

function CreateError(fun, name) {
    fun.prototype = Object.create(ParsingError.prototype);
    fun.prototype.name = name;
    fun.prototype.constructor = ParsingError;
}

function MissingBracketsError(error) {
    ParsingError.call(this, error);
}

CreateError(MissingBracketsError, "MissingBracketsError")

function InvalidArgumentError(error) {
    ParsingError.call(this, error);
}

CreateError(InvalidArgumentError, "InvalidArgumentError")


function BaseParser(str) {
    this.separators = ['(', ')', ''];
    this.pos = 0;
    this.str = str;
    this.getPos = () => this.str[this.pos];
    this.checkSeparator = (ch) => this.separators.includes(ch);
    this.removePos = (n) => this.pos -= n;
}

BaseParser.prototype.hasNext = function () {
    return this.pos + 1 <= this.str.length;
}

BaseParser.prototype.skipWhitespaces = function () {
    while (this.hasNext() && this.str[this.pos].trim() === "") {
        this.take();
    }
}

BaseParser.prototype.take = function () {
    this.pos++;
}

BaseParser.prototype.getValue = function () {
    let result = "";
    while (this.hasNext() && !(this.checkSeparator(this.getPos().trim()))) {
        result += this.getPos();
        this.take();
    }
    return result;
}

function ExpressionParser(expr) {
    const parser = new BaseParser(expr);
    let checkOpeningBracket = 0;
    let checkClosingBracket = 0;
    let waitForOperation = false;

    function parse() {
        let result = parseExpression();
        parser.skipWhitespaces();
        if (parser.hasNext()) {
            throw new InvalidArgumentError("No arguments expected after expression");
        } else if (checkOpeningBracket > checkClosingBracket) {
            throw new MissingBracketsError("Missing closing bracket");
        }
        return result;
    }

    function parseExpression() {
        parser.skipWhitespaces();
        if (parser.getPos() === '(') {
            waitForOperation = true;
            checkOpeningBracket++;
            parser.take();
            let result = parseExpression();
            parser.skipWhitespaces();
            if (waitForOperation) {
                throw new InvalidArgumentError("expected operation, but found " + parser.getPos());
            }
            if (parser.getPos() === ')') {
                parser.take();
                checkClosingBracket++;
            } else {
                throw new MissingBracketsError("Expected \')\', but found " + parser.getPos());
            }
            return result;
        }
        return parseOperation();
    }

    function parseOperation() {
        parser.skipWhitespaces();
        let val = parser.getValue();
        if (val in operations) {
            waitForOperation = false;
            let operation = operations[val];
            return new operation[0](...getValue(operation[1]));
        } else {
            parser.removePos(val.length);
            return getValue(1)[0];
        }
    }

    function getValue(count) {
        let stack = [];
        for (let i = 0; i < count; i++) {
            parser.skipWhitespaces();
            if (parser.getPos() === '(') {
                checkOpeningBracket++;
                parser.take();
                stack.push(parseExpression());
                parser.skipWhitespaces();
                if (parser.getPos() === ')') {
                    parser.take();
                    checkClosingBracket++;
                } else {
                    throw new MissingBracketsError("Expected \')\', but found " + parser.getPos());
                }
            } else {
                let val = parser.getValue();
                if (val in getVariable) {
                    stack.push(new Variable(val));
                } else if (!isNaN(Number(val)) && val !== "") {
                    stack.push(new Const(Number(val)));
                } else {
                    throw new InvalidArgumentError("Expected expression argument, but found " + parser.getPos());
                }
            }
        }
        return stack;
    }

    return parse();
}

const parsePrefix = ExpressionParser;

