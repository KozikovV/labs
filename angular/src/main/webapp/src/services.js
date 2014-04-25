angular.module('service', ['ngResource'])
    .factory('Category', function ($resource) {
        var Category = $resource('/api/categories/:id');


        return Category;
    })
    .factory('Test', function ($resource) {
        var Test = $resource('/api/test/:id');


        return Test;
    })
    .factory('Tag', function ($resource) {
        var tag = $resource('/api/tag/:id');


        return tag;
    });