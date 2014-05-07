var adminkaControllers = angular.module('adminkaControllers',
    ['adminkaDirectives', 'service', 'angularFileUpload']);

adminkaControllers.controller('AdminkaMainCtrl', ['$scope', 'Config',
    function ($scope, Config) {
        $scope.items = Config.getAdminkaMenuItems();
    }]);

adminkaControllers.controller('AdminkaImagesCtrl', ['$scope', 'Image', '$upload',
    function ($scope, Image, $upload) {
        $scope.images = Image.query();
        console.log("images -------- ");
        console.log($scope.images.$resolved);

        $scope.addImage = function () {
            $scope.images.push({});
            console.log("images -------- ");
            console.log($scope.images.$resolved);
        }
        $scope.edit = function(image){
            image.edit=true;
            image.old = angular.extend({}, image, {});
        }
        $scope.cancel = function(image){
            angular.extend(image, image.old);
            delete image.old;
            image.edit=false;
        }
        $scope.save = function (image) {
            console.log('save()');
            console.log(image);



            $upload.upload({
                url: '/api/image/save',
                data: image
            }).progress(function (evt) {
                    console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                }).success(function (data) {
                    console.log('s');
                    console.log(data);
                    angular.extend(image, data);
                    image.edit=false;
                    delete image.old;
                }).error(function () {
                    console.log('error');
                });

        }
        $scope.remove = function (image) {
            $scope.images.splice($scope.images.indexOf(image), 1);
            console.log($scope.images.length);
        }
        $scope.fileSelect = function (image, file) {
            image.file = file[0];
        }
    }]);

adminkaControllers.controller('AdminkaUsersCtrl', ['$scope',
    function ($scope) {

    }])