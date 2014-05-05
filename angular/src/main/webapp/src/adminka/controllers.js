var adminkaControllers = angular.module('adminkaControllers',
    ['adminkaDirectives', 'service', 'angularFileUpload']);

adminkaControllers.controller('AdminkaMainCtrl', ['$scope', 'Config',
    function ($scope, Config) {
        $scope.items = Config.getAdminkaMenuItems();
    }]);

adminkaControllers.controller('AdminkaImagesCtrl', ['$scope', 'Image', '$upload',
    function ($scope, Image, $upload) {
        $scope.images = Image.query();

        $scope.addImage = function () {
            $scope.images.push({});
        }
        $scope.save = function (image) {
            console.log('save()');
            console.log(image);
        }
        $scope.remove = function (image) {
            $scope.images.splice($scope.images.indexOf(image), 1);
            console.log($scope.images.length);
        }
        $scope.fileSelect = function (image, file) {
//            console.log(file);
            image.file = file[0];
            console.log(image);

            $upload.upload({
                url: '/api/image/save',
                file: file[0],
                data: image
            }).progress(function (evt) {
                    console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                }).success(function () {
                    console.log('s');
                }).error(function () {
                    console.log('error');
                });
        }
    }]);

adminkaControllers.controller('AdminkaUsersCtrl', ['$scope',
    function ($scope) {

    }])