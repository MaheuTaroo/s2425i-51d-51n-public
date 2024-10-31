function createElement(
    name: string | boolean,
    attributes?: Record<string, string | boolean | number | Array<Boolean>> | null,
    ...children: Array<string | boolean | HTMLElement>
): HTMLElement {
    throw new Error("TODO")
}

// Valid usages

createElement("div")
createElement("p", null, "Hello World")
createElement("a", {href: "https://www.typescriptlang.org"}, "typescript")
createElement("button", {disabled: true}, "Press me")
createElement("ul", null, 
    createElement("li", null, "Item 1"),
    createElement("li", null, "Item 2"),
)
const items = [
    "item 1",
    "item 2",
]
createElement("ul", null,
    items.map(item => createElement("li", null, item))
)

// invalid usages
createElement(true)
createElement("a", {foo: 1 })
createElement("a", {}, true)
createElement("a", {}, [true])