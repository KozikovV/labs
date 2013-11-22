function extend(Parent, Child){
    var F = function(){};
    F.prototype = Parent.prototype;
    Child.prototype = new F();
    Child.prototype.constructor =  Child;
    Child.superlass = Parent.prototype;
    return Child;
}