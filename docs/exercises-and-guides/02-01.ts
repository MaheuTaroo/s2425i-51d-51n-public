// primitive types
const aString: string = "hello"
const aNumber: number = 12345
const aBoolean: boolean = true

// arrays
const aStringArray: Array<string> = ["Hello", "World"]

// object types
type Student = {
    name: string,
    nbr: number,
}

const alice: Student = {
    name: "Alice",
    nbr: 12345
}

// sum types
function doSomething(input: string) {

    if (typeof input === "string") {
        console.log(input.toUpperCase())
    } else {
        console.log(input - 3)
    }
}

const arrayOfStringOrNumbers: Array<string | number> = ["Alice", 12345]

// discriminated sum types
type Result = 
| {kind: "network-error", error: Error}
| {kind: "http-response", status: number}

function doAnotherThing(result: Result) {
    switch(result.kind) {
        case "network-error": {
            console.log(result.error)
            break;
        }
        case "http-response": {
            console.log(result.kind)
            break;
        }
    }
}