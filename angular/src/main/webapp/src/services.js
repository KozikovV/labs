angular.module('service', ['ngResource'])
    .factory('Category', function ($resource) {
        var Category = $resource('/api/categories/:id');


        return Category;
    })
    .factory('Test', function ($resource) {
        var Test = $resource('/api/test/:id', {},
            {update: { method: 'PUT' }});


        return Test;
    })
    .factory('Tag', function ($resource) {
        var tag = $resource('/api/tag/:id');


        return tag;
    })
    .factory('Image', function ($resource) {
        var Image = $resource('/api/image/:id');


        return Image;
    })
    .factory('Config', function ($resource) {
        var Config = $resource('/api/config/clientSide');
        //TODO | need to do synchronosly and cash
        Config.load = function () {
            return Config.get();
        };
        Config.getAdminkaMenuItems = function () {
            return [
                {
                    key: 'toSite',
                    name: 'To Site',
                    description: 'Go to site to see your changes.',
                    url: '/',
                    icon: 'toSite.jpg'
                },
                {
                    key: 'images',
                    name: 'Images Config',
                    description: 'You can change, add, remove images there.',
                    url: '/adminka/images',
                    icon: 'images.jpg'
                },
                {
                    key: 'users',
                    name: 'Users',
                    description: 'You can edit, add, remove users there.',
                    url: '/adminka/users',
                    icon: 'users.jpg'
                }
            ]
        }
        return Config;
    })
;